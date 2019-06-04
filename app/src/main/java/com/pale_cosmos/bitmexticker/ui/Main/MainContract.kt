package com.pale_cosmos.bitmexticker.ui.Main

import android.content.Context

interface MainContract {
    interface View{
        fun changeStatusBar()
        fun initPresenter()
        fun hideActionBar()
        fun setInApplication()
        var appContext: Context
    }
    interface Presenter{

    }
}