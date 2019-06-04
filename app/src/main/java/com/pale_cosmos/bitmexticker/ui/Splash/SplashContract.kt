package com.pale_cosmos.bitmexticker.ui.Splash

interface SplashContract {
    interface View {
        fun changeStatusBar()
        fun hideActionBar()
        fun initPresenter()
        fun moveToMain()
        fun finishActivity()
        fun setInApplication()
    }

    interface Presenter {
        fun startPresent()
        fun moveToMain()
    }
}