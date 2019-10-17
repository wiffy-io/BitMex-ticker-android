package io.wiffy.bitmexticker.ui.subscription

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.model.Component
import kotlinx.android.synthetic.main.activitty_subscription.*

class SubscriptionActivity : SubscriptionContract.View() {

    private lateinit var mPresenter: SubscriptionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_D)
        setContentView(R.layout.activitty_subscription)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()

        mPresenter = SubscriptionPresenter(this, applicationContext)
        mPresenter.initPresent()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Component.subscription_on = true
        finish()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    override fun onStart() {
        super.onStart()
        Component.subscription_on = false
    }

    override fun onStop() {
        super.onStop()
        Component.subscription_on = true
    }

    override fun initializeUI() {
        subscribeButton.setOnClickListener {
            toast("구독")
        }

        policyButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wiffy.io/bitmex/PRIVACY-POLICY.txt")))
        }

        purchaseRestoreButton.setOnClickListener {
            toast("구매복원")
        }
    }

}