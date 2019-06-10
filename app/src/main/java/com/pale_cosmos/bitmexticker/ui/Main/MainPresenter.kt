package com.pale_cosmos.bitmexticker.ui.Main

import android.view.View
import com.pale_cosmos.bitmexticker.extension.cal_value
import com.pale_cosmos.bitmexticker.extension.change_value
import com.pale_cosmos.bitmexticker.extension.getUrlText
import com.pale_cosmos.bitmexticker.model.BitMEX_soket
import com.pale_cosmos.bitmexticker.model.Coin_info
import com.pale_cosmos.bitmexticker.model.Util
import org.json.JSONObject
import java.lang.Exception
import java.net.URI
import java.util.concurrent.ConcurrentHashMap

class MainPresenter(act: MainContract.View) : MainContract.Presenter {

    private val mView = act
    private var init_coin_ = ArrayList<Coin_info>()
    private var socket = BitMEX_soket(URI("wss://www.bitmex.com/realtime"))
    private var istouch = false

    override fun get_coin() = Thread(Runnable {
        try {
            var get_server = "http://jungh0.com/symbol".getUrlText().split("\n")
            for (i in 0 until get_server.size) {
                var tmp = get_server[i].split(",")
                /*
                var hmap = ConcurrentHashMap<String, String>()
                hmap["Symbol"] = tmp[0]
                hmap["price"] = tmp[1]
                hmap["is_new"] = tmp[2]
                hmap["name_info"] = tmp[3]
                hmap["before_p"] = tmp[4]
                hmap["chart_symbol"] = tmp[5]
                hmap["parse_str"] = tmp[5]
                init_coin.add(hmap)*/
                var data = Coin_info(tmp[0],tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],tmp[6])
                init_coin_.add(data)
                //Log.d("asdasd",init_coin[i].get("Symbol"))
            }
            mView.set_recycler(init_coin_)
        } catch (e: Exception) {
        }
    }).start()

    override fun change_UI() {
        //테마 상태에 따라 ui 디자인해줌
        mView.changeUI()
        val listener_theme = View.OnClickListener {
            Util.dark_theme = Util.dark_theme xor true
            mView.changeUI()
            Util.sharedPreferences_editor_theme.putBoolean(
                "mode",
                Util.dark_theme
            ).apply()

            mView.update_recycler_theme()
        }
        val listener_setting = View.OnClickListener {
            mView.moveToSetting()
        }
        mView.addBrightnessListener(listener_theme)
        mView.addSettingActivityChangeListener(listener_setting)
    }

    override fun make_socket() {
        socket.set_callback {
            socket_callback(it)
        }
        socket.set_sendback {
            socket_subscribe()
        }
        socket.connect()
    }

    private fun socket_subscribe() {
        // a .. b - 1 -> a until b
        for (i in 0 until init_coin_.size) {
            var tmp = init_coin_.get(i).Symbol.toString()
            socket.send_msg_filter("subscribe", "tradeBin1m", tmp)
        }
    }

    private fun socket_callback(it: String) {
        //Log.d("asdasd",it)
        // a .. b - 1 의 형태보다 a until b 의 형태가 권장되어 바꾸어줌.
        // set의 경우 replace가 권장되어 바꾸어줌, 하지만 replace의 경우 대상이 존재하지 않으면 set을 하지 않기때문에 문제가 발생할 수 있음
        for (i in 0 until init_coin_.size) {
            var tmp_symbol = init_coin_.get(i).Symbol.toString()
            try {
                val json_contact = JSONObject(it)
                //Log.d("asdasd",json_contact.toString())
                val table_name = json_contact.getString("table")
                if (table_name == "tradeBin1m") {
                    var data = json_contact.getJSONArray("data").getJSONObject(0)
                    val symbol = data.getString("symbol")
                    if (symbol == tmp_symbol) {
                        socket.send_msg_filter("unsubscribe", "tradeBin1m", tmp_symbol)
                        socket.send_msg_filter("subscribe", "trade", tmp_symbol)

                        val price = data.getDouble("close")
                        init_coin_.get(i).price = change_value(price)
                    }
                } else if (table_name == "trade") {
                    var data = json_contact.getJSONArray("data").getJSONObject(0)
                    val symbol = data.getString("symbol")
                    if (symbol == tmp_symbol) {
                        val price = data.getDouble("price")
                        val before = init_coin_.get(i).price ?: "0"
                        if (before.toDouble() < price) {
                            init_coin_.get(i).before_p = "g"
                        } else if (before.toDouble() > price) {
                            init_coin_.get(i).before_p = "r"
                        }
                        init_coin_.get(i).price = change_value(price)
                    }
                }
            } catch (e: Exception) {
            }
        }
        if (!istouch){
            mView.update_recycler(init_coin_)
        }
    }

    override fun onTouch() {
        istouch = true
    }

    override fun notonTouch() {
        istouch = false
    }

}