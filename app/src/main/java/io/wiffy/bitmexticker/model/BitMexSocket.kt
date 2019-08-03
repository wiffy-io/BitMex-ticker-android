package io.wiffy.bitmexticker.model

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class BitMexSocket(serverUri: URI) : WebSocketClient(serverUri) {

    var callBack:(String)->Unit? = {}
    var sendBack:(String)->Unit? = {}
    var closeBack:(String)->Unit? = {}

    fun setSendback(callback: ((String)->Unit)){
        sendBack = callback
    }

    fun set_callback(callback: ((String)->Unit)){
        callBack = callback
    }

    fun set_closeback(callback: ((String)->Unit)){
        closeBack = callback
    }

    fun send_msg(str:String){
        send(str)
    }

    fun sendMSGFilter(str1:String, str2:String, str3:String){
        send("{\"op\": \"$str1\", \"args\": [\"$str2:$str3\"]}")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        closeBack.invoke("")
    }

    override fun onMessage(message: String?) {
        if (message != null) {
            callBack.invoke(message)
        }
    }

    override fun onError(ex: Exception?) {
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        sendBack.invoke("")
    }


}