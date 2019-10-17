package io.wiffy.bitmexticker.ui.subscription

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import io.wiffy.bitmexticker.R

class SubscriptionActivity:SubscriptionContract.View() {

    private lateinit var mPresenter:SubscriptionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_D)
        setContentView(R.layout.activity_information)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()

        mPresenter = SubscriptionPresenter(this, applicationContext)

        mPresenter.initPresent()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    override fun initializeUI() {

    }

}