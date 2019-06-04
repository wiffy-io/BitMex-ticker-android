package com.pale_cosmos.bitmexticker.Splash

import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract


class SplashPresenter(act: SplashContract.View):SplashContract.Presenter {

    private val mView = act
    private val run = Runnable {
        mView.moveToMain()
        mView.finishActivity()
    }
    override fun startPresent() {
        moveToMain()
    }

    override fun moveToMain() {
        Handler().postDelayed(run,4000) // splashTest
    }
}