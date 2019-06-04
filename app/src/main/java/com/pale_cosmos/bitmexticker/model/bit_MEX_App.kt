package com.pale_cosmos.bitmexticker.model

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import android.view.Window
import android.view.WindowManager
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

        @JvmStatic
        lateinit var defaultPresenter: bit_MEX_AppPresenter

        @JvmStatic
        fun updateStatusBarColor(window: Window,color:String ) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(color)
        }
    }

    override fun onCreate() {
        super.onCreate()
        initPresenter()
    }

    override fun initPresenter() {
        mPresenter = bit_MEX_AppPresenter(this)
        defaultPresenter=mPresenter
        mPresenter.startPresent()
    }

    override fun onTerminate() {
        mPresenter.socket.close()
        super.onTerminate()
    }

}