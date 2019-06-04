package com.pale_cosmos.bitmexticker.Splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract


class SplashPresenter(act: SplashContract.View) : SplashContract.Presenter {

    private val mView = act


    @SuppressLint("CommitPrefEdits")
    override fun startPresent() {
        val sharedPreferences =
            mView.appContext.getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val flag = sharedPreferences.getBoolean("mode", true)
        editor.putBoolean("mode", flag)
        editor.apply()

        moveToMain(flag)

//        True -> Dark Mode (Deafault)
//        False -> Light Mode (Mode)
    }

    override fun moveToMain(flag: Boolean) {
        Handler().postDelayed({
            mView.moveToMain(flag)
            mView.finishActivity()
        }, 4000)
    }


}