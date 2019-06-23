package io.wiffy.bitmexticker.ui.information.orderBookFragment

import io.wiffy.bitmexticker.extension.changeValue
import io.wiffy.bitmexticker.model.MyApplication.Companion.socket
import org.json.JSONObject
import java.lang.Exception

class OrderBookPresenter(act: OrderBookConstract.View, sym: String) : OrderBookConstract.Presenter {

    //lateinit var socket:BitMEX_soket
    val mView = act
    lateinit var arr: ArrayList<OrderBookInfo>
    private var symbol = sym

    override fun init() {
        mView.changeUI()
        mView.set_recycler()
        mView.start_loading()
    }

    override fun start_ws() {
        //socket = BitMEX_soket(URI("wss://www.bitmex.com/realtime"))
        make_socket()
    }

    override fun stop_ws() {
        socket.send_msg_filter("unsubscribe", "orderBook10", symbol)
    }

    private fun make_socket() {
        socket.send_msg_filter("subscribe", "orderBook10", symbol)
        socket.set_callback {
            socket_callback(it)
        }
//        socket.set_sendback {
////
////        }
//        socket.set_closeback {
//            socket.close()
//        }
        //socket.connect()
    }

    private fun socket_callback(it: String) {

        if (it.contains("data") && it.contains("orderBook10")) {
            arr = ArrayList()
            try {
                val jsonContact = JSONObject(it)
                val data = jsonContact.getJSONArray("data")
                val bids = data.getJSONObject(0).getJSONArray("bids")
                val asks = data.getJSONObject(0).getJSONArray("asks")

                for (x in asks.length() - 1 downTo 0) {
                    arr.add(
                        OrderBookInfo(
                            asks.getJSONArray(x)[1].toString(),
                            changeValue(asks.getJSONArray(x)[0].toString().toDouble()),
                            null
                        )
                    )
                }
                for (x in 0 until bids.length()) {
                    arr.add(
                        OrderBookInfo(
                            null,
                            changeValue(bids.getJSONArray(x)[0].toString().toDouble()),
                            bids.getJSONArray(x)[1].toString()
                        )
                    )
                }
                mView.update_recycler(arr)
                mView.stop_loading()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        else if (it.contains("trade")) {
            val jsonContact = JSONObject(it)
            val tableName = jsonContact.getString("table")
            if (tableName == "trade") {
                val data = jsonContact.getJSONArray("data").getJSONObject(0)
                //Log.d("asdf",data.toString())
                val symbol2 = data.getString("symbol")
                if (symbol2 == symbol) {
                    val price = data.getDouble("price")
                    val size = data.getDouble("size")
                    mView.changeRecent("$symbol2 : ${changeValue(price)} - $size")
                }
            }
        }

    }

}