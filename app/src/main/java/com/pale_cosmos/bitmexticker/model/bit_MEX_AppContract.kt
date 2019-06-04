package com.pale_cosmos.bitmexticker.model

import android.app.Application
import android.view.Window
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
        var colorDark_navi: String
        var colorDark_table_out: String
        var colorDark_table_in: String
        var colorDark_title:String
        var colorDark_title2:String

        var colorLight_navi: String
        var colorLight_table_out: String
        var colorLight_table_in: String
        var colorLight_title:String
        var colorLight_title2:String
    }

    interface SocketClient {
        val msg: String
        var mPresenter: bit_MEX_AppPresenter
    }
}