package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

import android.util.Log
import com.pale_cosmos.bitmexticker.extension.change_value
import com.pale_cosmos.bitmexticker.model.BitMEX_soket
import org.json.JSONObject
import java.lang.Exception
import java.net.URI

class OrderBookPresenter(act: OrderBookConstract.View,sym:String) : OrderBookConstract.Presenter {

    lateinit var socket:BitMEX_soket
    val mView = act
    lateinit var arr:ArrayList<OrderBook_info>
    private var symbol = sym

    override fun init() {
        mView.changeUI()
        mView.set_recycler()
        mView.start_loading()
    }

    override fun start_ws() {
        socket = BitMEX_soket(URI("wss://www.bitmex.com/realtime"))
        make_socket()
    }

    override fun stop_ws() {
        socket.send_msg_filter("unsubscribe", "orderBook10", symbol)
    }

    private fun make_socket() {
        socket.set_callback {
            socket_callback(it)
        }
        socket.set_sendback {
            socket.send_msg_filter("subscribe", "orderBook10", symbol)
        }
        socket.set_closeback {
            socket.close()
        }
        socket.connect()
    }

    private fun socket_callback(it: String) {
        arr = ArrayList()
        try{
            var json_contact =JSONObject(it)
            var data =json_contact.getJSONArray("data")
            var bids = data.getJSONObject(0).getJSONArray("bids")
            var asks =data.getJSONObject(0).getJSONArray("asks")

            for(x in asks.length()-1 downTo  0)
            {
                arr.add(OrderBook_info(
                    asks.getJSONArray(x)[1].toString(),
                    change_value(asks.getJSONArray(x)[0].toString().toDouble()),
                    null))
            }
            for(x in 0 until bids.length())
            {
                arr.add(OrderBook_info(
                    null,
                    change_value(bids.getJSONArray(x)[0].toString().toDouble()),
                    bids.getJSONArray(x)[1].toString()))
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        mView.update_recycler(arr)
        mView.stop_loading()
    }


}