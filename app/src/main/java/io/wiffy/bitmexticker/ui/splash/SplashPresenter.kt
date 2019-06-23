package io.wiffy.bitmexticker.Splash

import android.os.Handler
import android.os.Looper
import android.util.Log
import io.wiffy.bitmexticker.extension.getUrlText
import io.wiffy.bitmexticker.ui.splash.SplashContract
import java.net.URL


class SplashPresenter(act: SplashContract.View) : SplashContract.Presenter {

    private val mView = act

    override fun startPresent() {
        mView.agreement()
    }

    override fun checkInternetConnection() {

        Thread(Runnable {
            try {
                val getServer = URL("http://wiffy.io/bitmex/").readText()
                Log.d("asdf",getServer)
                if (URL("http://wiffy.io/response").readText().contains("R")){
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