package com.pale_cosmos.bitmexticker.ui.Main

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R

class MainActivity :AppCompatActivity(),MainContract.View
{
    lateinit var mPresenter:MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeStatusBar()
        hideActionBar()
    }
    override fun changeStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#223863") // mainLight
    }

    override fun initPresenter() {

    }

    override fun hideActionBar() {
        supportActionBar?.hide()
    }
}