package com.pale_cosmos.bitmexticker.Splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract
import com.pale_cosmos.bitmexticker.ui.Splash.SplashTask
import org.intellij.lang.annotations.Language
import java.util.*


class SplashPresenter(act: SplashContract.View, cnt: Context) : SplashContract.Presenter {

    private val mView = act
    private val mContext = cnt

    override fun startPresent() {
        mView.argreement()
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