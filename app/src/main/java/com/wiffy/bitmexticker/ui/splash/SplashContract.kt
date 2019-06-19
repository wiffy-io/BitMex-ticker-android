package com.wiffy.bitmexticker.ui.splash


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