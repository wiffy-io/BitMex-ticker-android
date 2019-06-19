package com.wiffy.bitmexticker.ui.Information.DetailsFragment

class DetailsPresenter(act:DetailsConstract.View):DetailsConstract.Presenter {
    val mView = act
    override fun init() {
        mView.changeUI()
    }
}