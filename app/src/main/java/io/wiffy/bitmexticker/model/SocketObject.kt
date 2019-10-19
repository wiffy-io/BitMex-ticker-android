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
        Log.d("asdads", "{\"op\": \"$str1\", \"args\": [\"$str2:$str3\"]}")
        send("{\"op\": \"$str1\", \"args\": [\"$str2:$str3\"]}")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        closeBack.invoke("")
    }

    override fun onMessage(message: String?) {
        message?.let {
            callBack.invoke(it)
        }
    }

    override fun onError(ex: Exception?) {
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        sendBack.invoke("")
    }
}