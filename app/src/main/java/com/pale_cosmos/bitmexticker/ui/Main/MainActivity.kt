package com.pale_cosmos.bitmexticker.ui.Main

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.bit_MEX_App

class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var mPresenter: MainPresenter
    lateinit var menuButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeStatusBar()
        hideActionBar()
        setInApplication()
        initPresenter()
    }

    override fun changeStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#223863") // mainLight
    }

    override fun initPresenter() {
        mPresenter = MainPresenter(this)
    }

    override fun hideActionBar() {
        supportActionBar?.hide()

    }

    override fun setInApplication() {
        bit_MEX_App.main = this
    }
}