package io.wiffy.bitmexticker.ui.setting

import android.app.AlertDialog
import android.widget.CompoundButton


interface SettingContract {
    interface View
    {
        fun changeUI()
        fun moveToMain()
        fun addTickerButtonListener(listener: android.view.View.OnClickListener)
        fun addSettingButtonListener(listener1: android.view.View.OnClickListener,
                                     listener2: android.view.View.OnClickListener,
                                     listener3: android.view.View.OnClickListener,
                                     listener4: android.view.View.OnClickListener,
                                     listener5: android.view.View.OnClickListener,
                                     listener6:CompoundButton.OnCheckedChangeListener)
        fun startDialog(title:String,context:String):AlertDialog
        fun getStringTo(id:Int):String
        fun clipOnBoard(clipBoardMessage:String)
        fun urlParseToMarket(url:String)
        fun openLanguageSetting()
    }

    interface Presenter
    {
        fun changeUI()
        fun setSystemLanguage():Boolean
    }
}