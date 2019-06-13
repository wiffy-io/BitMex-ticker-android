package com.pale_cosmos.bitmexticker.Splash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract


class SplashPresenter(act: SplashContract.View, cnt: Context) : SplashContract.Presenter {

    private val mView = act
    private val mContext = cnt

    @SuppressLint("CommitPrefEdits")
    override fun startPresent() {
        Util.sharedPreferences_theme = mContext.getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        Util.sharedPreferences_editor_theme = Util.sharedPreferences_theme.edit() // editor를 static으로 선언함으로써 변경을 용이하게함

        Util.dark_theme = Util.sharedPreferences_theme.getBoolean("mode", true)
        moveToMain()
    }

    private fun moveToMain() {
        Handler(Looper.getMainLooper()).postDelayed({
            mView.moveToMain()
        }, 650)
    }

}