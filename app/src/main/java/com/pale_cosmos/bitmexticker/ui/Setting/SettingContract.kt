package com.pale_cosmos.bitmexticker.ui.Setting

import android.view.View

interface SettingContract {
    interface View
    {
        fun changeDark()
        fun changeLight()
        fun moveToMain()
        fun addTickerButtonListener(listener: android.view.View.OnClickListener)
        fun addSettingButtonListener(listener1: android.view.View.OnClickListener,
                                     listener2: android.view.View.OnClickListener,
                                     listener3: android.view.View.OnClickListener,
                                     listener4: android.view.View.OnClickListener)
        fun startDialog(title:String,context:String)
    }

    interface Presenter
    {
        fun change_UI()

    }
}