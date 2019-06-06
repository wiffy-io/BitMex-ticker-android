package com.pale_cosmos.bitmexticker.model

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class BitMEX_soket: WebSocketClient {

    //var msg:String = ""
    var callback_:(String)->Unit?
    var sendback_:(String)->Unit?

    constructor(serverUri: URI) : super(serverUri) {
        callback_ = {}
        sendback_ = {}
    }

    fun set_sendback(callback: ((String)->Unit)){
        sendback_ = callback
    }

    fun set_callback(callback: ((String)->Unit)){
        callback_ = callback
    }

    fun send_msg(str:String){
        send(str)
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {

    }

    override fun onMessage(message: String?) {
        if (message != null) {
            callback_.invoke(message)
        }
    }

    override fun onError(ex: Exception?) {

    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        //send(msg)
        sendback_.invoke("")
    }

}