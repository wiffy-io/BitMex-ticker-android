package io.wiffy.bitmexticker.ui.information.notificationFragment

import android.content.Context
import io.wiffy.bitmexticker.model.SuperContract

interface NotificationContract {
    abstract class View : SuperContract.SuperFragment() {
        abstract fun changeUI()
        abstract fun builderUp()
        abstract fun builderDismiss()
        abstract fun sendContext(): Context?
    }

    interface Presenter : SuperContract.WiffyObject {
        fun init()
    }
}