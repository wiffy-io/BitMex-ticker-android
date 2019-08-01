package io.wiffy.bitmexticker.ui.information.notificationFragment

import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.NotificationInfo


class NotificationPresenter(private val mView:NotificationContract.View):NotificationContract.Presenter {

    override fun init() {
        mView.changeUI()
    }

    override fun doList(list: ArrayList<NotificationInfo>) {

    }
}