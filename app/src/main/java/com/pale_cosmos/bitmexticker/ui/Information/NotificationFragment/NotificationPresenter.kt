package com.pale_cosmos.bitmexticker.ui.Information.NotificationFragment



class NotificationPresenter(act:NotificationConstract.View):NotificationConstract.Presenter {
    private val mView = act

    override fun init() {
        mView.changeUI()
    }
}