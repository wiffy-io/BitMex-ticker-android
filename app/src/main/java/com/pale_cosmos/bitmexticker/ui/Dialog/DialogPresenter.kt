package com.pale_cosmos.bitmexticker.ui.Dialog

import android.view.View

class DialogPresenter(act:DialogContract.View):DialogContract.Presenter {
    var mView = act
    override fun init() {
        mView.addListenerOkButton(listener = View.OnClickListener{
            mView.moveToBack()
        })
    }
}