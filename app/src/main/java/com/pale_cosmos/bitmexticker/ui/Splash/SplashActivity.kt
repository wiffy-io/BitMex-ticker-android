package com.pale_cosmos.bitmexticker.Splash

import android.content.Context
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
    override var appContext = applicationContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        changeStatusBar()
        hideActionBar()
        setInApplication()
        initPresenter()

    }

    override fun changeStatusBar() {
        bit_MEX_App.updateStatusBarColor(
            window,
            bit_MEX_App.defaultPresenter.colorDark_table_out
        )
    }

    override fun hideActionBar() {
        supportActionBar?.hide()
    }

    override fun initPresenter() {
        mPresenter = SplashPresenter(this)
        mPresenter.startPresent()

    }

    override fun moveToMain(flag:Boolean) {
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("mode",flag)
        startActivity(intent)
        finish()
    }

    override fun setInApplication() {
        bit_MEX_App.splash = this
    }
}