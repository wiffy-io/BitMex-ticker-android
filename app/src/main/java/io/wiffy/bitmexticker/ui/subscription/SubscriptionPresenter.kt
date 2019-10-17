package io.wiffy.bitmexticker.ui.subscription

import android.content.Context

class SubscriptionPresenter(
    private val mView: SubscriptionContract.View,
    private val mContext: Context
) : SubscriptionContract.Presenter {
    override fun initPresent() {
        mView.initializeUI()
    }
}