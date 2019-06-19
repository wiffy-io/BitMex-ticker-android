package com.wiffy.bitmexticker.ui.Information.NotificationFragment



class NotificationPresenter(act:NotificationConstract.View):NotificationConstract.Presenter {
    private val mView = act

    override fun init() {
        mView.changeUI()
    }
}