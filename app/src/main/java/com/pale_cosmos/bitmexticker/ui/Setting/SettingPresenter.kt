package com.pale_cosmos.bitmexticker.ui.Setting

import android.view.View
import com.pale_cosmos.bitmexticker.model.Util

class SettingPresenter(act: SettingContract.View) : SettingContract.Presenter {
    private val mView = act

    override fun change_UI() {
        mView.changeStatusBarAndView(Util.dark_theme)
        mView.addTickerButtonListener(listener = View.OnClickListener {
            mView.moveToMain()
        })
    }
}