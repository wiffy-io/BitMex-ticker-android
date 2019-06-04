package com.pale_cosmos.bitmexticker.ui.Main

import android.util.Log
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.BitMEX_soket
import com.pale_cosmos.bitmexticker.model.Util
import java.net.URI


class MainPresenter(act: MainContract.View):MainContract.Presenter {
    private val mView = act

    override fun change_UI() {
        //테마 상태에 따라아 ui 디자인해줌
        if(Util.dark_theme){
            mView.changeStatusBar(R.color.dark_table_out)
        }else {
            mView.changeStatusBar(R.color.light_table_out)
        }
    }

    override fun make_socket() {
        var msg = "{\"op\": \"subscribe\", \"args\": [\"orderBook10:XBTUSD\"]}"
        var socket = BitMEX_soket(URI("wss://www.bitmex.com/realtime"),msg)
        socket.set_callback {
            //Log.d("TESTSTART2","msg : $it")
            mView.append_text(it)
        }
        socket.connect()
    }
}