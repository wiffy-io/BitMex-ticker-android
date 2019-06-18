package com.pale_cosmos.bitmexticker.ui.Information

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.*
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.model.Util.Companion.info_on
import com.pale_cosmos.bitmexticker.ui.Information.DetailsFragment.DetailsFragment
import com.pale_cosmos.bitmexticker.ui.Information.MainFragment.MainFragment
import com.pale_cosmos.bitmexticker.ui.Information.NotificationFragment.NotificationFragment
import com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment.OrderBookFragment
import kotlinx.android.synthetic.main.activity_information.*
import java.util.*
import kotlin.collections.ArrayList


class InformationActivity : AppCompatActivity(),
    InformationContract.View, GestureDetector.OnGestureListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var mPresenter: InformationPresenter
    lateinit var gestureScanner: GestureDetector
    lateinit var coinInformation: String
    lateinit var myBundle: Bundle
    lateinit var fragmentList: ArrayList<Fragment?>

    var preFrag: Fragment? = null
    var catches = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_information)
        supportActionBar?.hide()
        coinInformation = intent.getStringExtra("information")
        information_navi.setOnNavigationItemSelectedListener(this)
        information_navi.menu.findItem(R.id.action_title).title = coinInformation
        mPresenter = InformationPresenter(this, applicationContext)
        gestureScanner = GestureDetector(this)
        mPresenter.init()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (catches != item.itemId) {
            when (item.itemId) {
                R.id.action_title -> {
                    viewFragment_Main()
                }
                R.id.action_book -> {
                    viewFragment_OrderBook()
                }
                R.id.action_detail -> {
                    viewFragment_Details()
                }
                R.id.action_notification -> {
                    viewFragment_Notification()
                }
            }
            catches = item.itemId
        }
        return true
    }


    override fun changeUI() {
        myBundle = Bundle()
        myBundle.putString("symbol", coinInformation)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(get_navi())
        window.navigationBarColor = resources.getColor(darkAndLight_reverse())
        information_toolbar.background = resources.getDrawable(get_navi())
        information_title.text = coinInformation
        parent_information.background = resources.getDrawable(get_table_out())
        information_navi.background = resources.getDrawable(get_bottom())
        information_navi.itemRippleColor =
            ContextCompat.getColorStateList(this@InformationActivity, rippleAndTintOfBottom())
        information_navi.itemIconTintList =
            ContextCompat.getColorStateList(this@InformationActivity, get_bottom_color())
        information_navi.itemTextColor = ContextCompat.getColorStateList(this@InformationActivity, get_bottom_color())
        toMainFromInformation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24, 0, 0, 0)
        initFragment()
    }

    override fun initFragment() {

        fragmentList = ArrayList()
        fragmentList.add(null)
        fragmentList.add(null)
        fragmentList.add(null)
        fragmentList.add(null)

        viewFragment_Main()
        catches = R.id.action_title

    }

    override fun viewFragment_Main() {
        for (i in 0 until fragmentList.size) {
            when (i) {
                0 -> {
                    if (fragmentList[i] == null) {
                        fragmentList[i] = MainFragment()
                        fragmentList[i]?.arguments = myBundle
                        supportFragmentManager.beginTransaction().add(R.id.information_frame, fragmentList[i]!!)
                            .commit()
                    }
                }
                else -> {
                    if (fragmentList[i] != null) {
                        supportFragmentManager.beginTransaction().hide(fragmentList[i]!!).commit()
                    }
                }
            }
        }
        supportFragmentManager.beginTransaction().show(fragmentList[0]!!).commit()
    }

    override fun viewFragment_OrderBook() {
        for (i in 0 until fragmentList.size) {
            when (i) {
                1 -> {
                    if (fragmentList[i] == null) {
                        fragmentList[i] = OrderBookFragment()
                        fragmentList[i]?.arguments = myBundle
                        supportFragmentManager.beginTransaction().add(R.id.information_frame, fragmentList[i]!!)
                            .commit()
                    }
                }
                else -> {
                    if (fragmentList[i] != null) {
                        supportFragmentManager.beginTransaction().hide(fragmentList[i]!!).commit()
                    }
                }
            }
        }
        supportFragmentManager.beginTransaction().show(fragmentList[1]!!).commit()
    }

    override fun viewFragment_Details() {
        for (i in 0 until fragmentList.size) {
            when (i) {
                2 -> {
                    if (fragmentList[i] == null) {
                        fragmentList[i] = DetailsFragment()
                        fragmentList[i]?.arguments = myBundle
                        supportFragmentManager.beginTransaction().add(R.id.information_frame, fragmentList[i]!!)
                            .commit()
                    }
                }
                else -> {
                    if (fragmentList[i] != null) {
                        supportFragmentManager.beginTransaction().hide(fragmentList[i]!!).commit()
                    }
                }
            }
        }
        supportFragmentManager.beginTransaction().show(fragmentList[2]!!).commit()
    }

    override fun viewFragment_Notification() {
        for (i in 0 until fragmentList.size) {
            when (i) {
                3 -> {
                    if (fragmentList[i] == null) {
                        fragmentList[i] = NotificationFragment()
                        fragmentList[i]?.arguments = myBundle
                        supportFragmentManager.beginTransaction().add(R.id.information_frame, fragmentList[i]!!)
                            .commit()
                    }
                }
                else -> {
                    if (fragmentList[i] != null) {
                        supportFragmentManager.beginTransaction().hide(fragmentList[i]!!).commit()
                    }
                }
            }
        }
        supportFragmentManager.beginTransaction().show(fragmentList[3]!!).commit()
    }

    override fun addTickerButtonListener(listener: View.OnClickListener) {
        toMainFromInformation.setOnClickListener(listener)
    }

    override fun moveToMain() {
        info_on = true
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

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }

    }

    override fun attachBaseContext(newBase: Context?) {

        super.attachBaseContext(
            Util.wrap(
                newBase,
                Util.global
            )
        )

    }
}
