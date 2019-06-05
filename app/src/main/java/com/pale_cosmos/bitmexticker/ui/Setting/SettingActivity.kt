package com.pale_cosmos.bitmexticker.ui.Setting

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.app_bar_setting.*

class SettingActivity:AppCompatActivity(),SettingContract.View {
    lateinit var mPresenter: SettingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mPresenter = SettingPresenter(this)
        mPresenter.change_UI()
    }

    override fun changeStatusBarAndView(theme: Boolean) {
        val toolbar_color:Int
        val background_color:Int

//        var text_color:Int

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        when(theme)
        {
            true->{
                toolbar_color = R.color.dark_navi
                background_color = R.color.dark_table_out
            }
            false->{
                toolbar_color = R.color.light_navi
                background_color = R.color.light_table_out
            }
        }
        window.statusBarColor = resources.getColor(toolbar_color)

        toolbar_setting.background = resources.getDrawable(toolbar_color)
        parent_setting.background = resources.getDrawable(background_color)

        toMainFromSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24,0,0,0)
    }

    override fun moveToMain() {
        finish()
        overridePendingTransition(R.anim.leftin_activity,R.anim.rightout_activity)
    }

    override fun addTickerButtonListener(listener: View.OnClickListener) {
        toMainFromSetting.setOnClickListener(listener)
    }

    override fun onBackPressed() {
        moveToMain()
    }
}