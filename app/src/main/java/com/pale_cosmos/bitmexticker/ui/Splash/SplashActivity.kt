package com.pale_cosmos.bitmexticker.Splash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.bit_MEX_App
import com.pale_cosmos.bitmexticker.ui.Main.MainActivity
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract

class SplashActivity : AppCompatActivity(), SplashContract.View {
    lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        changeStatusBar()
        hideActionBar()
        setInApplication()
        initPresenter()

    }

    override fun changeStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#092336") // mainDark
    }

    override fun hideActionBar() {
        supportActionBar?.hide()
    }

    override fun initPresenter() {
        mPresenter = SplashPresenter(this)
        mPresenter.startPresent()

    }

    override fun moveToMain() {
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun finishActivity() {
        finish()
    }

    override fun setInApplication() {
        bit_MEX_App.splash = this
    }
}