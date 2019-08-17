package io.wiffy.bitmexticker.ui.information

import android.view.GestureDetector
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

interface InformationContract {
    abstract class View : AppCompatActivity(), GestureDetector.OnGestureListener,
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

    interface Presenter {
        fun init()
        fun setSystemLanguage(): Boolean
    }
}