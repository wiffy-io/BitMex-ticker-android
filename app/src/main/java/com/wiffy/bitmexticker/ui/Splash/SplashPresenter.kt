package com.wiffy.bitmexticker.Splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.wiffy.bitmexticker.extension.getUrlText
import com.wiffy.bitmexticker.ui.Splash.SplashContract


class SplashPresenter(act: SplashContract.View, cnt: Context) : SplashContract.Presenter {

    private val mView = act
    private val mContext = cnt

    override fun startPresent() {
        mView.agreement()
    }

    override fun checkInternetConnection() {
        //SplashTask(mContext, this).execute()
        Thread(Runnable {
            try {
                var get_server = "http://jungh0.com/symbol".getUrlText()
                if (get_server.contains("XBTUSD")){
                    connectionOn(get_server)
                }else{
                    connectionOff()
                }
            } catch (e: Exception) {
                connectionOff()
            }
        }).start()
    }

    override fun connectionOn(str:String) {
        Handler(Looper.getMainLooper()).postDelayed({
            mView.moveToMain(str)
        }, 650)
    }

    override fun connectionOff() {
        mView.getOut()
    }

}