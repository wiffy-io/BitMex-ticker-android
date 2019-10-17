package io.wiffy.bitmexticker.ui.subscription

import io.wiffy.bitmexticker.model.SuperContract

interface SubscriptionContract {
    abstract class View : SuperContract.SuperActivity() {
        abstract fun initializeUI()
    }

    interface Presenter : SuperContract.WiffyObject {
        fun initPresent()
    }
}