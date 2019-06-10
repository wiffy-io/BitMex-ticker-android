package com.pale_cosmos.bitmexticker.ui.Main

import android.os.Bundle
import android.view.View
import com.pale_cosmos.bitmexticker.model.Coin_info
import java.util.concurrent.ConcurrentHashMap


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
    }
    interface Presenter{
        fun change_UI()
        fun make_socket()
        fun get_coin()
    }
}