package com.pale_cosmos.bitmexticker.model

import java.net.URI

class bit_MEX_AppPresenter(app: bit_MEX_App) : bit_MEX_AppContract.Presenter {

    override val bit_Address = "wss://www.bitmex.com/realtime"
    override var myApp = app
    override lateinit var uri: URI
    override lateinit var socket: Client

    override var colorDark_navi="#223863"
    override var colorDark_table_out="#082335"
    override var colorDark_table_in="#183148"
    override var colorDark_title="#ffffffff"
    override var colorDark_title2="#61ffffff"

    override var colorLight_navi="#45A4B3"
    override var colorLight_table_out="#efeff3"
    override var colorLight_table_in="#fff2ff"
    override var colorLight_title="#ff303030"
    override var colorLight_title2="#ff646464"

    override fun startPresent() {
        setSocket()
    }

    override fun setSocket() {
        uri = URI(bit_Address)
        socket = Client(uri,this)
        socket.connect()
    }
}