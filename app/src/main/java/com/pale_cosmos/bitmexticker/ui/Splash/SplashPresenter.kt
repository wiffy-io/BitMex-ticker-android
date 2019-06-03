package com.pale_cosmos.bitmexticker.Splash

import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract


class SplashPresenter(act: SplashContract.View):SplashContract.Presenter {

    val mView = act

    override fun startPresent() {
        moveToMain()
    }

    override fun moveToMain() {
        Handler().postDelayed({mView.moveToMain()},1200)
    }
}