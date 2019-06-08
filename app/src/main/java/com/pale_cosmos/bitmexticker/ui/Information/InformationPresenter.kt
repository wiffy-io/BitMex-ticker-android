package com.pale_cosmos.bitmexticker.ui.Information

import android.view.View

class InformationPresenter(act:InformationContract.View):InformationContract.Presenter {

    private val mView = act
    override fun init() {
        mView.changeUI()
        mView.addTickerButtonListener(listener = View.OnClickListener {
            mView.moveToMain()
        })
    }
}