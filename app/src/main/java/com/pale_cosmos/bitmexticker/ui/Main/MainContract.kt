package com.pale_cosmos.bitmexticker.ui.Main

import android.view.View
import java.util.concurrent.ConcurrentHashMap


interface MainContract {
    interface View{
        fun changeUI()
        fun addBrightnessListener(listener:android.view.View.OnClickListener)
        fun addSettingActivityChangeListener(listener: android.view.View.OnClickListener)
        fun moveToSetting()
        fun set_recycler(init_coin:ArrayList<ConcurrentHashMap<String, String>>)
        fun update_recycler(mod_coin:ArrayList<ConcurrentHashMap<String, String>>)
        fun update_recycler_theme()
    }
    interface Presenter{
        fun change_UI()
        fun make_socket()
        fun get_coin()
    }
}