package com.pale_cosmos.bitmexticker.ui.Splash

import android.content.Context


interface SplashContract {
    interface View {
        fun moveToMain(str:String)
        fun getOut()
        fun agreement()
    }

    interface Presenter {
        fun startPresent()
        fun checkInternetConnection()
        fun connectionOn(str:String)
        fun connectionOff()
    }
}