package com.pale_cosmos.bitmexticker.Splash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract


class SplashPresenter(act: SplashContract.View, cnt: Context) : SplashContract.Presenter {

    private val mView = act
    private val mContext = cnt

    @SuppressLint("CommitPrefEdits")
    override fun startPresent() {
        val sharedPreferences = mContext.getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        Util.dark_theme = sharedPreferences.getBoolean("mode", true)
        moveToMain()
        //True -> Dark Mode (Default)
        //False -> Light Mode (Mode)
    }

    private fun moveToMain() {
        Handler().postDelayed({
            mView.moveToMain()
            //mView.finishActivity()
        }, 1000)
    }

}