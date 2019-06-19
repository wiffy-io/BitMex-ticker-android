package com.wiffy.bitmexticker.ui.information.detailsFragment

class DetailsPresenter(act:DetailsContract.View):DetailsContract.Presenter {
    val mView = act
    override fun init() {
        mView.changeUI()
    }
}