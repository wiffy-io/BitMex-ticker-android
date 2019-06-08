package com.pale_cosmos.bitmexticker.ui.Information

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_navi
import com.pale_cosmos.bitmexticker.extension.get_table_out
import kotlinx.android.synthetic.main.activity_information.*

const val SWIPE_MIN_DISTANCE = 120
const val SWIPE_MAX_OFF_PATH = 250
const val SWIPE_THRESHOLD_VELOCITY = 150

class InformationActivity : AppCompatActivity(), InformationContract.View, GestureDetector.OnGestureListener {
    lateinit var mPresenter: InformationPresenter
    lateinit var gestureScanner: GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        supportActionBar?.hide()
        mPresenter = InformationPresenter(this)
        gestureScanner = GestureDetector(this)
        mPresenter.init()

    }

    override fun changeUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(get_navi())
        information_toolbar.background = resources.getDrawable(get_navi())
        parent_information.background = resources.getDrawable(get_table_out())
        //add this
        toMainFromInformation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24, 0, 0, 0)
    }

    override fun addTickerButtonListener(listener: View.OnClickListener) {
        toMainFromInformation.setOnClickListener(listener)
    }

    override fun moveToMain() {
        finish()
        overridePendingTransition(R.anim.leftin_activity, R.anim.rightout_activity)
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
        if (e2!!.x - e1!!.x > com.pale_cosmos.bitmexticker.ui.Setting.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > com.pale_cosmos.bitmexticker.ui.Setting.SWIPE_THRESHOLD_VELOCITY) {
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