package com.pale_cosmos.bitmexticker.ui.Splash

import android.content.Context


interface SplashContract {
    interface View {
        fun moveToMain()
        fun getOut()
        fun argreement()
    }

    interface Presenter {
        fun startPresent()
        fun checkInternetConnection()
        fun connectionOn()
        fun connectionOff()
    }
}