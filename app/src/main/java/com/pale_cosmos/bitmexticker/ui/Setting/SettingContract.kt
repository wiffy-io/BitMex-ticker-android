package com.pale_cosmos.bitmexticker.ui.Setting

import android.view.View

interface SettingContract {
    interface View
    {
        fun changeDark()
        fun changeLight()
        fun moveToMain()
        fun addTickerButtonListener(listener: android.view.View.OnClickListener)
    }

    interface Presenter
    {
        fun change_UI()

    }
}