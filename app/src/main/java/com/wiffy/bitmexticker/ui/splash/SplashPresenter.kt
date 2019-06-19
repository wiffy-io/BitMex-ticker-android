package com.wiffy.bitmexticker.Splash

import android.os.Handler
import android.os.Looper
import com.wiffy.bitmexticker.extension.getUrlText
import com.wiffy.bitmexticker.ui.splash.SplashContract


class SplashPresenter(act: SplashContract.View) : SplashContract.Presenter {

    private val mView = act

    override fun startPresent() {
        mView.agreement()
    }

    override fun checkInternetConnection() {

        Thread(Runnable {
            try {
                val getServer = "http://jungh0.com/symbol".getUrlText()
                if (getServer.contains("XBTUSD")){
                    connectionOn(getServer)
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