package com.wiffy.bitmexticker.ui.information.notificationFragment



class NotificationPresenter(act:NotificationContract.View):NotificationContract.Presenter {
    private val mView = act

    override fun init() {
        mView.changeUI()
    }
}