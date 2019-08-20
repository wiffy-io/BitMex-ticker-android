package io.wiffy.bitmexticker.ui.splash

import io.wiffy.bitmexticker.model.SuperContract


interface SplashContract {
    abstract class View : SuperContract.SuperActivity() {
        abstract fun moveToMain(str: String)
        abstract fun getOut(): Boolean
        abstract fun resultOK()
        abstract fun resultCancel()
    }

    interface Presenter : SuperContract.WiffyObject {
        fun checkInternetConnection()
        fun connectionOn(str: String): Boolean
        fun connectionOff(): Boolean
    }
}