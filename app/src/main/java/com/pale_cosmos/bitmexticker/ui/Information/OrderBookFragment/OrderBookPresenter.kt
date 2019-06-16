package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

import android.util.Log
import com.pale_cosmos.bitmexticker.model.BitMEX_soket
import java.net.URI

class OrderBookPresenter(act: OrderBookConstract.View,sym:String) : OrderBookConstract.Presenter {

    lateinit var socket:BitMEX_soket
    val mView = act
    lateinit var arr:ArrayList<OrderBook_info>
    private var symbol = sym

    override fun init() {
        mView.changeUI()
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
        Log.d("asdf",it)
        arr = ArrayList()
        arr.add(OrderBook_info("0111","0223","230"))
        mView.update_recycler(arr)
    }
}