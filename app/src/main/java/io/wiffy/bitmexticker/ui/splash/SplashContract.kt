package io.wiffy.bitmexticker.ui.splash

import androidx.appcompat.app.AppCompatActivity


interface SplashContract {
    abstract class View : AppCompatActivity() {
        abstract fun moveToMain(str: String)
        abstract fun getOut(): Boolean
    }

    interface Presenter {
        fun checkInternetConnection()
        fun connectionOn(str: String): Boolean
        fun connectionOff(): Boolean
    }
}