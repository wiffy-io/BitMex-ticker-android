package com.pale_cosmos.bitmexticker.ui.Main

interface MainContract {
    interface View{
        fun changeStatusBar()
        fun initPresenter()
        fun hideActionBar()
        fun setInApplication()
    }
    interface Presenter{

    }
}