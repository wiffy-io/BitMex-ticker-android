package io.wiffy.bitmexticker.ui.splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import io.wiffy.bitmexticker.extension.getConnectivityStatus
import java.net.URL


class SplashPresenter(private val mView: SplashContract.View, private val mContext: Context) : SplashContract.Presenter {

    override fun checkInternetConnection() = Thread(Runnable {
        try {
            if (getConnectivityStatus(mContext)){
                val getServer = URL("http://wiffy.io/bitmex/").readText()
                if (getServer.contains("XBTUSD")) {
                    connectionOn(getServer)
                } else {
                    connectionOff()
                }
            }else{
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