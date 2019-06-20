package com.wiffy.bitmexticker.model

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class BitMEX_soket(serverUri: URI) : WebSocketClient(serverUri) {

    //var msg:String = ""
    var callback_:(String)->Unit?
    var sendback_:(String)->Unit?
    var closeback:(String)->Unit?

    init {
        callback_ = {}
        sendback_ = {}
        closeback = {}
    }

    fun set_sendback(callback: ((String)->Unit)){
        sendback_ = callback
    }

    fun set_callback(callback: ((String)->Unit)){
        callback_ = callback
    }

    fun set_closeback(callback: ((String)->Unit)){
        closeback = callback
    }

    fun send_msg(str:String){
        send(str)
    }

    fun send_msg_filter(str1:String,str2:String,str3:String){
        send("{\"op\": \"$str1\", \"args\": [\"$str2:$str3\"]}")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        //Log.d("asdf","close")
        closeback.invoke("")
    }

    override fun onMessage(message: String?) {
        if (message != null) {
            callback_.invoke(message)
        }
    }

    override fun onError(ex: Exception?) {
        //Log.d("asdf","erroe")
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        //send(msg)
        sendback_.invoke("")
    }


}