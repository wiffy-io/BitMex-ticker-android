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
                mView.startDialog("OpenSource", mView.getStringTo(R.string.open_source))
            },
            listener2 = View.OnClickListener {
                mView.startDialog("Version", mView.getStringTo(R.string.version))
            },
            listener3 = View.OnClickListener {
                mView.urlParseToMarket(mView.getStringTo(R.string.store_url))
            },
            listener4 = View.OnClickListener {
                mView.clipOnBoard(mView.getStringTo(R.string.addmin_mail))
                mView.startDialog(mView.getStringTo(R.string.addmin_mail), "Copied email")
            })
    }
}