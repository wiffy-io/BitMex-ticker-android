package com.pale_cosmos.bitmexticker.ui.Main

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.BitMEX_soket
import com.pale_cosmos.bitmexticker.model.Util
import java.net.URI


class MainPresenter(act: MainContract.View):MainContract.Presenter {
    private val mView = act

    override fun change_UI() {
        //테마 상태에 따라아 ui 디자인해줌

        if (Util.dark_theme){
            mView.changeDark()
        }else{
            mView.changeLight()
        }
//        mView.changeStatusBarAndView(Util.dark_theme)

        val listener_theme = View.OnClickListener {
            Util.dark_theme = Util.dark_theme xor true
            if(Util.dark_theme)mView.changeDark()
            else mView.changeLight()
            Util.sharedPreferences_editor_theme.putBoolean("mode",Util.dark_theme).apply()
        }
        val listener_setting = View.OnClickListener {
            mView.moveToSetting()
        }
        mView.addBrightnessListener(listener_theme)
        mView.addSettingActivityChangeListener(listener_setting)
    }

    override fun make_socket() {
        var msg = "{\"op\": \"subscribe\", \"args\": [\"orderBook10:XBTUSD\"]}"
        var socket = BitMEX_soket(URI("wss://www.bitmex.com/realtime"),msg)
        socket.set_callback {
            //            Log.d("testSet",it)
            mView.run {

            }
            mView.append_text(it)

        }
        socket.connect()
    }
}