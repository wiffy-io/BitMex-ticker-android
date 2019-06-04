package com.pale_cosmos.bitmexticker.model

import android.app.Application
import org.java_websocket.client.WebSocketClient
import java.net.URI

interface bit_MEX_AppContract {

    interface Application {
        fun initPresenter()
        var mPresenter: bit_MEX_AppPresenter
    }

    interface Presenter {
        fun startPresent()
        fun setSocket()
        val bit_Address: String
        var myApp: bit_MEX_App
        var uri: URI
        var socket: Client
    }

    interface SocketClient {
        val msg: String
        var mPresenter: bit_MEX_AppPresenter
    }
}