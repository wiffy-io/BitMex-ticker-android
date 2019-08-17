package io.wiffy.bitmexticker.ui.information.notificationFragment

import androidx.fragment.app.Fragment

interface NotificationContract {
    abstract class View : Fragment() {
        abstract fun changeUI()
    }

    interface Presenter {
        fun init()
    }
}