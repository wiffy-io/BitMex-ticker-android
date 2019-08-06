package io.wiffy.bitmexticker.ui.splash


interface SplashContract {
    interface View {
        fun moveToMain(str: String)
        fun getOut(): Boolean
    }

    interface Presenter {
        fun checkInternetConnection()
        fun connectionOn(str: String): Boolean
        fun connectionOff(): Boolean
    }
}