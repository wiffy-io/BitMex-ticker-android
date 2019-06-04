package com.pale_cosmos.bitmexticker.ui.Splash

import android.content.Context

interface SplashContract {
    interface View {
        fun changeStatusBar()
        fun hideActionBar()
        fun initPresenter()
        fun moveToMain(flag: Boolean)
        fun setInApplication()
        var appContext:Context
    }

    interface Presenter {
        fun startPresent()
        fun moveToMain(flag:Boolean)
    }
}