package io.wiffy.bitmexticker.ui.information.notificationFragment

import io.wiffy.bitmexticker.model.SuperContract

interface NotificationContract {
    abstract class View : SuperContract.SuperFragment() {
        abstract fun changeUI()
        abstract fun builderUp()
        abstract fun builderDismiss()
    }

    interface Presenter:SuperContract.WiffyObject {
        fun init()
    }
}