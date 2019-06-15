package com.pale_cosmos.bitmexticker.Splash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract
import com.pale_cosmos.bitmexticker.ui.Splash.SplashTask


class SplashPresenter(act: SplashContract.View, cnt: Context) : SplashContract.Presenter {

    private val mView = act
    private val mContext = cnt

    @SuppressLint("CommitPrefEdits")
    override fun startPresent() {
        Util.sharedPreferences = mContext.getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        Util.sharedPreferences_editor = Util.sharedPreferences.edit() // editor를 static으로 선언함으로써 변경을 용이하게함

        Util.global = Util.sharedPreferences.getString("global", "en")
        Util.dark_theme = Util.sharedPreferences.getBoolean("mode", true)
        checkInternetConnection()
    }

    override fun checkInternetConnection() {
        SplashTask(mContext,this).execute()
    }


    override fun connectionOn() {
        Handler(Looper.getMainLooper()).postDelayed({
            mView.moveToMain()
        }, 650)
    }

    override fun connectionOff() {
        mView.getOut()
    }


}