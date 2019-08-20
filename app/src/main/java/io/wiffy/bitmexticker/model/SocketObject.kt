package io.wiffy.bitmexticker.model

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

object SocketObject : WebSocketClient(URI("wss://www.bitmex.com/realtime")) {
    var callBack: (String) -> Unit? = {}
    var sendBack: (String) -> Unit? = {}
    var closeBack: (String) -> Unit? = {}

    fun send_msg(str: String) {
        send(str)
    }

    fun sendMSGFilter(str1: String, str2: String, str3: String) {
        send("{\"op\": \"$str1\", \"args\": [\"$str2:$str3\"]}")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.d("asdfvv", "socket disconnected")
        closeBack.invoke("")
    }

    override fun onMessage(message: String?) {
        Log.d("asdfvv", "socket messaged")
        message?.let {
            callBack.invoke(it)
        }
    }

    override fun onError(ex: Exception?) {
        Log.d("asdfvv", "socket error")
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.d("asdfvv", "socket connected")
        sendBack.invoke("")
    }
}