package com.pale_cosmos.bitmexticker.ui.Setting

import android.view.View
import com.pale_cosmos.bitmexticker.model.Util

class SettingPresenter(act: SettingContract.View) : SettingContract.Presenter {
    private val mView = act

    override fun change_UI() {
        if (Util.dark_theme){
            mView.changeDark()
        }else{
            mView.changeLight()
        }
        mView.addTickerButtonListener(listener = View.OnClickListener {
            mView.moveToMain()
        })
    }
}