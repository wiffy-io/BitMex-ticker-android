package io.wiffy.bitmexticker.ui.setting

import android.app.AlertDialog
import android.view.GestureDetector
import android.widget.CompoundButton
import io.wiffy.bitmexticker.model.SuperContract


interface SettingContract {
    abstract class View : SuperContract.SuperActivity(), GestureDetector.OnGestureListener {
        abstract fun changeUI()
        abstract fun moveToMain()
        abstract fun addTickerButtonListener(listener: android.view.View.OnClickListener)
        abstract fun addSettingButtonListener(
            listener1: android.view.View.OnClickListener,
            listener2: android.view.View.OnClickListener,
            listener3: android.view.View.OnClickListener,
            listener4: android.view.View.OnClickListener,
            listener5: android.view.View.OnClickListener,
            listener6: CompoundButton.OnCheckedChangeListener
        )

        abstract fun startDialog(title: String, context: String): AlertDialog
        abstract fun getStringTo(id: Int): String
        abstract fun clipOnBoard(clipBoardMessage: String)
        abstract fun urlParseToMarket(url: String)
        abstract fun openLanguageSetting()
    }

    interface Presenter : SuperContract.WiffyObject {
        fun changeUI()
        fun setSystemLanguage(): Boolean
    }
}