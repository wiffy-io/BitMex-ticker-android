package com.pale_cosmos.bitmexticker.ui.Main

import android.view.View


interface MainContract {
    interface View{
        fun changeStatusBarAndView(theme:Boolean)
        fun append_text(str:String)
        fun addBrightnessListener(listener:android.view.View.OnClickListener)
        fun addSettingActivityChangeListener(listener: android.view.View.OnClickListener)
        fun moveToSetting()
    }
    interface Presenter{
        fun change_UI()
        fun make_socket()
    }
}