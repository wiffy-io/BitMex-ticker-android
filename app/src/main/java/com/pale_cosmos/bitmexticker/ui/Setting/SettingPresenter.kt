package com.pale_cosmos.bitmexticker.ui.Setting

import android.view.View
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.Util

class SettingPresenter(act: SettingContract.View) : SettingContract.Presenter {
    private val mView = act

    override fun change_UI() {
        if (Util.dark_theme) {
            mView.changeDark()
        } else {
            mView.changeLight()
        }
        mView.addTickerButtonListener(
            listener = View.OnClickListener {
            mView.moveToMain()
        })
        mView.addSettingButtonListener(
            listener1 = View.OnClickListener {
                mView.startDialog("OpenSource", R.string.open_srouce)
            },
            listener2 = View.OnClickListener {

            },
            listener3 = View.OnClickListener {

            },
            listener4 = View.OnClickListener {

            })
    }
}