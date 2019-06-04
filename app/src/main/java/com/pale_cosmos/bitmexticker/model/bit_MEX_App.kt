package com.pale_cosmos.bitmexticker.model

import android.annotation.SuppressLint
import android.app.Application
import com.pale_cosmos.bitmexticker.Splash.SplashActivity
import com.pale_cosmos.bitmexticker.ui.Main.MainActivity
import java.net.URI


open class bit_MEX_App : Application(), bit_MEX_AppContract.Application {
    override lateinit var mPresenter: bit_MEX_AppPresenter
    companion object{
        @JvmStatic
        lateinit var splash:SplashActivity


        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var main:MainActivity
    }

    override fun onCreate() {
        super.onCreate()
        initPresenter()
    }

    override fun initPresenter() {
        mPresenter = bit_MEX_AppPresenter(this)
        mPresenter.startPresent()
    }

    override fun onTerminate() {
        mPresenter.socket.close()
        super.onTerminate()
    }
}