package io.wiffy.bitmexticker.ui.splash

import android.os.Handler
import android.os.Looper
import java.net.URL


class SplashPresenter(private val mView: SplashContract.View) : SplashContract.Presenter {

    override fun startPresent() = mView.agreement()


    override fun checkInternetConnection() = Thread(Runnable {
        try {
            val getServer = URL("http://wiffy.io/bitmex/").readText()
            if (URL("http://wiffy.io/response").readText().contains("R")) {
                connectionOn(getServer)
            } else {
                connectionOff()
            }
        } catch (e: Exception) {
            connectionOff()
        }
    }).start()


    override fun connectionOn(str: String) =
        Handler(Looper.getMainLooper()).postDelayed({
            mView.moveToMain(str)
        }, 650)


    override fun connectionOff() = mView.getOut()


}