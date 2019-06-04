package com.pale_cosmos.bitmexticker.model

import android.util.Log
import android.widget.Toast
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class Client : WebSocketClient, bit_MEX_AppContract.SocketClient {
    override val msg = "{\"op\": \"subscribe\", \"args\": [\"orderBook10:XBTUSD\"]}"
    override var mPresenter: bit_MEX_AppPresenter

    constructor(serverUri: URI, draft: Draft, presenter: bit_MEX_AppPresenter)
            : super(serverUri, draft) {
        mPresenter = presenter
    }

    constructor(serverUri: URI, presenter: bit_MEX_AppPresenter) : super(serverUri) {
        mPresenter = presenter
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
//        Log.d("TESTSTART","SOCKET_ON")
        send(msg)
//        Log.d("TESTSTART",msg)
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
//        Log.d("TESTSTART","SOCKET_OFF")
    }

    override fun onMessage(message: String?) {
//        Log.d("TESTSTART","msg : $message")
    }

    override fun onError(ex: Exception?) {
//        Log.d("TESTSTART","Error")
//        ex?.printStackTrace()
    }
}