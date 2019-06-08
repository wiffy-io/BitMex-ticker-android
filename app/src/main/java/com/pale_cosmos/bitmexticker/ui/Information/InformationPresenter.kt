package com.pale_cosmos.bitmexticker.ui.Information

class InformationPresenter(act:InformationContract.View):InformationContract.Presenter {

    private val mView = act
    override fun init() {
        mView.changeUI()
    }
}