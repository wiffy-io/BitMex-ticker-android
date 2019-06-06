package com.pale_cosmos.bitmexticker.ui.Main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.ui.Setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var mPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        test.text = "앙기모띠"

        mPresenter = MainPresenter(this)
        mPresenter.change_UI()
        mPresenter.make_socket()


    }


    override fun changeDark() {

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.dark_navi)
        brightness_main.setImageResource(R.drawable.to_darks)
        toolbar_main.background = resources.getDrawable(R.color.dark_navi)
        parent_main.background = resources.getDrawable(R.color.dark_table_out)
    }

    override fun changeLight() {

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.light_navi)
        brightness_main.setImageResource(R.drawable.to_lights)
        toolbar_main.background = resources.getDrawable(R.color.light_navi)
        parent_main.background = resources.getDrawable(R.color.light_table_out)
    }

    override fun append_text(str: String) {
//        Log.d("testset", str)
        Handler(applicationContext.mainLooper).post { test.text = str }


    }

    override fun addBrightnessListener(listener: View.OnClickListener) {
        brightness_main.setOnClickListener(listener)
    }

    override fun addSettingActivityChangeListener(listener: View.OnClickListener) {
        setting_main.setOnClickListener(listener)
    }

    override fun moveToSetting() {
        startActivity(Intent(this@MainActivity, SettingActivity::class.java))
        overridePendingTransition(R.anim.rightin_activity, R.anim.leftout_activity)
    }
}