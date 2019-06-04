package com.pale_cosmos.bitmexticker.model

import java.net.URI

class bit_MEX_AppPresenter(app: bit_MEX_App) : bit_MEX_AppContract.Presenter {

    override val bit_Address = "wss://www.bitmex.com/realtime"
    override var myApp = app
    override lateinit var uri: URI
    override lateinit var socket: Client

    override fun startPresent() {
        setSocket()
    }

    override fun setSocket() {
        uri = URI(bit_Address)
        socket = Client(uri,this)
        socket.connect()
    }
}