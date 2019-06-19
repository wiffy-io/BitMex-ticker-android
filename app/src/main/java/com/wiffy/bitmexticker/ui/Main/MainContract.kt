package com.wiffy.bitmexticker.ui.Main

import android.os.Bundle
import com.wiffy.bitmexticker.model.Coin_info


interface MainContract {
    interface View{
        fun changeUI()
        fun addBrightnessListener(listener:android.view.View.OnClickListener)
        fun addSettingActivityChangeListener(listener: android.view.View.OnClickListener)
        fun moveToSetting()
        fun set_recycler(init_coin:ArrayList<Coin_info>)
        fun update_recycler(mod_coin:ArrayList<Coin_info>)
        fun update_recycler_theme()
        fun moveToInformation(bundle:Bundle)
        fun change_recent(str:String)
        fun start_loading()
        fun stop_loading()
        fun check_loading():Boolean
    }
    interface Presenter{
        fun change_UI()
        fun make_socket()
        fun get_coin(str:String)
        fun socket_reconnect()
        fun setSystemLanguage()
    }
}