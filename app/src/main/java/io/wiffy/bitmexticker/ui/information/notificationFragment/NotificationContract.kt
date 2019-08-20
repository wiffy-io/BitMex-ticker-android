package io.wiffy.bitmexticker.ui.information.notificationFragment

import io.wiffy.bitmexticker.model.SuperContract

interface NotificationContract {
    abstract class View : SuperContract.SuperFragment() {
        abstract fun changeUI()
    }

    interface Presenter:SuperContract.WiffyObject {
        fun init()
    }
}