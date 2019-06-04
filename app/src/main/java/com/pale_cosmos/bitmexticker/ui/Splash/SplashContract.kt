package com.pale_cosmos.bitmexticker.ui.Splash


interface SplashContract {
    interface View {
        fun moveToMain()
    }

    interface Presenter {
        fun startPresent()
    }
}