package com.pale_cosmos.bitmexticker.model

import android.app.Application
import java.net.URI


open class bit_MEX_App : Application(), bit_MEX_AppContract.Application {
    override lateinit var mPresenter: bit_MEX_AppPresenter


    override fun onCreate() {
        super.onCreate()
        initPresenter()
    }

    override fun initPresenter() {
        mPresenter = bit_MEX_AppPresenter(this)
        mPresenter.startPresent()
    }


}