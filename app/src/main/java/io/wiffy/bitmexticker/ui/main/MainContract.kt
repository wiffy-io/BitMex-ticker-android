package io.wiffy.bitmexticker.ui.main

import android.os.Bundle
import io.wiffy.bitmexticker.model.CoinInfo


interface MainContract {
    interface View{
        fun changeUI()
        fun addSettingActivityChangeListener(listener: android.view.View.OnClickListener)
        fun moveToSetting()
        fun setRecycler(init_coin:ArrayList<CoinInfo>)
        fun updateRecycler(mod_coin:ArrayList<CoinInfo>)
        fun updateRecyclerTheme()
        fun moveToInformation(bundle:Bundle)
        fun changeRecent(str:String)
        fun startLoading()
        fun stopLoading()
        fun checkLoading():Boolean
        fun tossSymbol(symbol:String)
        fun tossXBT(xbt:String)
        fun initViewPager()
    }
    interface Presenter{
        fun changeUI()
        fun makeSocket()
        fun getCoin(str:String)
        fun socketReconnect()
        fun setSystemLanguage()
        fun setSymbol(str:String?)
        fun parseViewPager():ArrayList<String>
    }
}