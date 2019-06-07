package com.pale_cosmos.bitmexticker.ui.Main

import android.os.Handler
import android.util.Log
import android.view.View
import com.pale_cosmos.bitmexticker.extension.change_value
import com.pale_cosmos.bitmexticker.extension.getUrlText
import com.pale_cosmos.bitmexticker.model.BitMEX_soket
import com.pale_cosmos.bitmexticker.model.Util
import org.json.JSONObject
import java.lang.Exception
import java.net.URI
import java.util.concurrent.ConcurrentHashMap

class MainPresenter(act: MainContract.View):MainContract.Presenter {

    private val mView = act
    private var init_coin = ArrayList<ConcurrentHashMap<String,String>>()
    private var socket = BitMEX_soket(URI("wss://www.bitmex.com/realtime"))

    override fun get_coin() {
        Thread(Runnable {
            try{
                var get_server = "http://jungh0.com/symbol".getUrlText().split("\n")
                for(i in 0..get_server.size-1){
                    var tmp = get_server[i].split(",")
                    var hmap = ConcurrentHashMap<String,String>()
                    hmap.put("Symbol",tmp[0])
                    hmap.put("price",tmp[1])
                    hmap.put("is_new",tmp[2])
                    hmap.put("name_info",tmp[3])
                    hmap.put("before_p",tmp[4])
                    hmap.put("chart_symbol",tmp[5])
                    hmap.put("parse_str",tmp[5])
                    init_coin.add(hmap)
                    //var data = Coin_info(tmp[0],tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],tmp[6])
                    //Log.d("asdasd",init_coin[i].get("Symbol"))
                }
                mView.set_recycler(init_coin)
            }catch (e:Exception){
            }
        }).start()
    }

    override fun change_UI() {
        //테마 상태에 따라아 ui 디자인해줌

        if (Util.dark_theme){
            mView.changeDark()
        }else{
            mView.changeLight()
        }
//        mView.changeStatusBarAndView(Util.dark_theme)

        val listener_theme = View.OnClickListener {
            Util.dark_theme = Util.dark_theme xor true
            if(Util.dark_theme)mView.changeDark()
            else mView.changeLight()
            Util.sharedPreferences_editor_theme.putBoolean("mode",Util.dark_theme).apply()
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

    private fun socket_subscribe(){
        for(i in 0..init_coin.size-1){
            var tmp = init_coin[i].get("Symbol").toString()
            socket.send_msg_filter("subscribe","tradeBin1m",tmp)
        }
    }

    private fun socket_callback(it:String){
        //Log.d("asdasd",it)
        for(i in 0..init_coin.size-1){
            var tmp_symbol = init_coin[i].get("Symbol").toString()
            try{
                val json_contact = JSONObject(it)
                //Log.d("asdasd",json_contact.toString())
                val table_name = json_contact.getString("table")
                if (table_name == "tradeBin1m"){
                    var data = json_contact.getJSONArray("data").getJSONObject(0)
                    val symbol = data.getString("symbol")
                    if (symbol == tmp_symbol){
                        socket.send_msg_filter("unsubscribe","tradeBin1m",tmp_symbol)
                        socket.send_msg_filter("subscribe","trade",tmp_symbol)

                        val price = data.getDouble("close")
                        init_coin[i].set("price",change_value(price))
                    }
                }else if (table_name == "trade"){
                    var data = json_contact.getJSONArray("data").getJSONObject(0)
                    val symbol = data.getString("symbol")
                    if (symbol == tmp_symbol){
                        val price = data.getDouble("price")
                        init_coin[i].set("price",change_value(price))
                    }
                }
            }catch (e: Exception){
            }
        }
        mView.update_recycler(init_coin)
    }
}