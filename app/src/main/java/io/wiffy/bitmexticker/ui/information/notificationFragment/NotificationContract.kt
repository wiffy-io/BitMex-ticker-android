package io.wiffy.bitmexticker.ui.information.notificationFragment

interface NotificationContract {
    interface View{
        fun changeUI()
    }
    interface Presenter{
        fun init()
    }
}