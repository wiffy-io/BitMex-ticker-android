package com.pale_cosmos.bitmexticker.ui.Main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
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

        mPresenter = MainPresenter(this)
        mPresenter.change_UI()
        mPresenter.make_socket()

    }

    override fun changeStatusBarAndView(theme:Boolean) {
        val toolbar_color:Int
        val background_color:Int
        val image:Int
//        var text_color:Int

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        when(theme)
        {
            true->{
                toolbar_color = R.color.dark_navi
                background_color = R.color.dark_table_out
                image =R.drawable.outline_wb_sunny_24
            }
            false->{
                toolbar_color = R.color.light_navi
                background_color = R.color.light_table_out
                image =R.drawable.outline_brightness_3_24
            }
        }
        window.statusBarColor = resources.getColor(toolbar_color)
        brightness_main.setImageResource(image)
        toolbar_main.background = resources.getDrawable(toolbar_color)
        parent_main.background = resources.getDrawable(background_color)
    }

    override fun append_text(str: String) {
        test.text = str
    }

    override fun addBrightnessListener(listener: View.OnClickListener) {
        brightness_main.setOnClickListener(listener)
    }

    override fun addSettingActivityChangeListener(listener: View.OnClickListener) {
        setting_main.setOnClickListener(listener)
    }

    override fun moveToSetting() {
        startActivity(Intent(this@MainActivity, SettingActivity::class.java))
        overridePendingTransition(R.anim.rightin_activity,R.anim.leftout_activity)
    }
}