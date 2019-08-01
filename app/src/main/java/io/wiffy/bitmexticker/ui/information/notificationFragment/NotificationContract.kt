package io.wiffy.bitmexticker.ui.information.notificationFragment

import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.NotificationInfo

interface NotificationContract {
    interface View{
        fun changeUI()
    }
    interface Presenter{
        fun init()
        fun doList(list:ArrayList<NotificationInfo>)
    }
}