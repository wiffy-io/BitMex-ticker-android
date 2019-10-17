package io.wiffy.bitmexticker.ui.subscription

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import com.anjlab.android.iab.v3.BillingProcessor
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.restartApp
import io.wiffy.bitmexticker.function.wrap
import io.wiffy.bitmexticker.model.BillingModule
import io.wiffy.bitmexticker.model.Component
import kotlinx.android.synthetic.main.activitty_subscription.*

class SubscriptionActivity : SubscriptionContract.View() {

    private lateinit var mPresenter: SubscriptionPresenter
    private var mBillingProcessor: BillingProcessor? = null
    private var billingModule: BillingModule? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_D)
        setContentView(R.layout.activitty_subscription)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()

        billingModule = BillingModule(this)
        billingModule?.initBillingProcessor()
        mBillingProcessor = billingModule?.getBillingProcessor()

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
            billingModule?.purchaseProduct()
        }

        policyButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.wiffy.io/bitmex/PRIVACY-POLICY.txt")
                )
            )
        }

        purchaseRestoreButton.setOnClickListener {
            billingModule?.aaaaaa()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (mBillingProcessor?.handleActivityResult(requestCode, resultCode, data) == true) {
            if (resultCode == Activity.RESULT_OK) {
                restartApp(this)
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) = super.attachBaseContext(
        wrap(
            newBase,
            Component.global
        )
    )

}