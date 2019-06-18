package com.pale_cosmos.bitmexticker.Splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract
import com.pale_cosmos.bitmexticker.ui.Splash.SplashTask


class SplashPresenter(act: SplashContract.View, cnt: Context) : SplashContract.Presenter {

    private val mView = act
    private val mContext = cnt

    override fun startPresent() {
        mView.agreement()
    }


    override fun checkInternetConnection() {
        SplashTask(mContext, this).execute()
    }


    override fun connectionOn() {
        Handler(Looper.getMainLooper()).postDelayed({
            mView.moveToMain()
        }, 650)
    }

    override fun connectionOff() {
        mView.getOut()
    }


}