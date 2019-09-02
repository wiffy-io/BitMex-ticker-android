package io.wiffy.bitmexticker.ui.main

import android.os.Bundle
import io.wiffy.bitmexticker.model.SuperContract
import io.wiffy.bitmexticker.model.data.CoinInfo


interface MainContract {
    abstract class View : SuperContract.SuperActivity() {
        abstract fun changeUI()
        abstract fun addSettingActivityChangeListener(listener: android.view.View.OnClickListener)
        abstract fun moveToSetting()
        abstract fun setRecycler(init_coin: ArrayList<CoinInfo>)
        abstract fun updateRecycler(mod_coin: ArrayList<CoinInfo>): Boolean
        abstract fun updateRecyclerTheme(): Boolean
        abstract fun moveToInformation(bundle: Bundle)
        abstract fun changeRecent(str: String)
        abstract fun startLoading()
        abstract fun stopLoading()
        abstract fun checkLoading(): Boolean
        abstract fun tossSymbol(symbol: String): Boolean
        abstract fun tossXBT(xbt: String): Boolean
        abstract fun initInformation()
        abstract fun setInformation(list: ArrayList<String>?)
        abstract fun popUp()
    }

    interface Presenter : SuperContract.WiffyObject {
        fun changeUI()
        fun makeSocket()
        fun getCoin(str: String)
        fun socketReconnect()
        fun setSystemLanguage(): Boolean
        fun setSymbol(str: String?)
        fun parseInformation(): ArrayList<String>
    }
}