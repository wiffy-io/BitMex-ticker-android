package com.pale_cosmos.bitmexticker.ui.Information

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_bottom
import com.pale_cosmos.bitmexticker.extension.get_bottom_color
import com.pale_cosmos.bitmexticker.extension.get_navi
import com.pale_cosmos.bitmexticker.extension.get_table_out
import kotlinx.android.synthetic.main.activity_information.*


class InformationActivity : AppCompatActivity(),
    InformationContract.View, GestureDetector.OnGestureListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var mPresenter: InformationPresenter
    lateinit var gestureScanner: GestureDetector
    lateinit var coinInformation: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        supportActionBar?.hide()
        coinInformation = intent.getStringExtra("information")
        information_navi.setOnNavigationItemSelectedListener(this)
        information_navi.menu.findItem(R.id.action_title).title = coinInformation
        mPresenter = InformationPresenter(this)
        gestureScanner = GestureDetector(this)
        mPresenter.init()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_title -> {

            }
            R.id.action_book -> {

            }
            R.id.action_detail -> {

            }
        }
        return true
    }


    override fun changeUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(get_navi())
        information_toolbar.background = resources.getDrawable(get_navi())
        information_title.text = coinInformation
        parent_information.background = resources.getDrawable(get_table_out())
        information_navi.background = resources.getDrawable(get_bottom())
        information_navi.itemRippleColor = ContextCompat.getColorStateList(this@InformationActivity, get_navi())
        information_navi.itemIconTintList = ContextCompat.getColorStateList(this@InformationActivity, get_bottom_color())
        information_navi.itemTextColor = ContextCompat.getColorStateList(this@InformationActivity, get_bottom_color())
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
