package io.wiffy.bitmexticker.ui.information

import android.view.GestureDetector
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.wiffy.bitmexticker.model.SuperContract

interface InformationContract {
    abstract class View : SuperContract.SuperActivity(), GestureDetector.OnGestureListener,
        BottomNavigationView.OnNavigationItemSelectedListener {
        abstract fun changeUI()
        abstract fun addTickerButtonListener(listener: android.view.View.OnClickListener)
        abstract fun moveToMain()
        abstract fun initFragment()
        abstract fun viewFragmentDetails()
        abstract fun viewFragmentMain()
        abstract fun viewFragmentOrderBook()
        abstract fun viewFragmentNotification()
    }

    interface Presenter : SuperContract.WiffyObject {
        fun init()
        fun setSystemLanguage(): Boolean
    }
}