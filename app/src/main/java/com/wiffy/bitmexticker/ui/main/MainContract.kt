package com.wiffy.bitmexticker.ui.main

import android.os.Bundle
import com.wiffy.bitmexticker.model.Coin_info


interface MainContract {
    interface View{
        fun changeUI()
        fun addBrightnessListener(listener:android.view.View.OnClickListener)
        fun addSettingActivityChangeListener(listener: android.view.View.OnClickListener)
        fun moveToSetting()
        fun setRecycler(init_coin:ArrayList<Coin_info>)
        fun updateRecycler(mod_coin:ArrayList<Coin_info>)
        fun updateRecyclerTheme()
        fun moveToInformation(bundle:Bundle)
        fun changeRecent(str:String)
        fun startLoading()
        fun stopLoading()
        fun checkLoading():Boolean
    }
    interface Presenter{
        fun changeUI()
        fun makeSocket()
        fun getCoin(str:String)
        fun socketReconnect()
        fun setSystemLanguage()
    }
}