package com.pale_cosmos.bitmexticker.ui.Setting


import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.Util
import kotlinx.android.synthetic.main.activity_setting_dark.*
import kotlinx.android.synthetic.main.app_bar_setting.*


const val SWIPE_MIN_DISTANCE = 120
const val SWIPE_MAX_OFF_PATH = 250
const val SWIPE_THRESHOLD_VELOCITY = 200


class SettingActivity : AppCompatActivity(), SettingContract.View, GestureDetector.OnGestureListener {
    lateinit var mPresenter: SettingPresenter
    lateinit var gestureScanner: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Util.dark_theme) setContentView(R.layout.activity_setting_dark)
        else setContentView(R.layout.activity_setting_light)

        gestureScanner = GestureDetector(this)
        mPresenter = SettingPresenter(this)
        mPresenter.change_UI()
    }


    override fun changeDark() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.dark_navi)

        toolbar_setting.background = resources.getDrawable(R.color.dark_navi)
        parent_setting.background = resources.getDrawable(R.color.dark_table_out)

        toMainFromSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24, 0, 0, 0)
    }

    override fun changeLight() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.light_navi)

        toolbar_setting.background = resources.getDrawable(R.color.light_navi)
        parent_setting.background = resources.getDrawable(R.color.light_table_out)

        toMainFromSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24, 0, 0, 0)
    }

    override fun moveToMain() {
        finish()
        overridePendingTransition(R.anim.leftin_activity, R.anim.rightout_activity)
    }

    override fun addTickerButtonListener(listener: View.OnClickListener) {
        toMainFromSetting.setOnClickListener(listener)
    }

    override fun onBackPressed() {
        moveToMain()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureScanner.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        if(e2!!.x-e1!!.x> SWIPE_MIN_DISTANCE && Math.abs(velocityX)> SWIPE_THRESHOLD_VELOCITY)
        {
            moveToMain()
        }

        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

}