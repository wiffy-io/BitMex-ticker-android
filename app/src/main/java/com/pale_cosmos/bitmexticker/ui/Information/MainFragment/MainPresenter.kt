package com.pale_cosmos.bitmexticker.ui.Information.MainFragment

class MainPresenter(act:MainConstract.View):MainConstract.Presenter {
    private val mView = act

    override fun init() {
        mView.changeUI()
    }
}