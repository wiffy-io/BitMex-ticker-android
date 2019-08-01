package io.wiffy.bitmexticker.ui.information.notificationFragment



class NotificationPresenter(private val mView:NotificationContract.View):NotificationContract.Presenter {

    override fun init() {
        mView.changeUI()
    }
}