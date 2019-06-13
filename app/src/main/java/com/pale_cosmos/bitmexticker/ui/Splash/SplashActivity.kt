package com.pale_cosmos.bitmexticker.Splash

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.ui.Main.MainActivity
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract

class SplashActivity : AppCompatActivity(), SplashContract.View {
    lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        changeStatusBar()

        mPresenter = SplashPresenter(this,applicationContext)
        mPresenter.startPresent()

    }

    private fun changeStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.dark_table_out);
    }

    override fun moveToMain() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.abc_fade_in,R.anim.not_move_activity)
        finish()
    }
    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }

    }
}