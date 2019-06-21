package com.wiffy.bitmexticker.ui.information

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.*
import com.wiffy.bitmexticker.model.CoinInfo
import com.wiffy.bitmexticker.model.Util
import com.wiffy.bitmexticker.model.Util.Companion.info_on
import com.wiffy.bitmexticker.model.Util.Companion.inforContext
import com.wiffy.bitmexticker.ui.information.detailsFragment.DetailsFragment
import com.wiffy.bitmexticker.ui.information.mainFragment.MainFragment
import com.wiffy.bitmexticker.ui.information.notificationFragment.NotificationFragment
import com.wiffy.bitmexticker.ui.information.orderBookFragment.OrderBookFragment
import kotlinx.android.synthetic.main.activity_information.*
import kotlin.collections.ArrayList
import kotlin.math.abs


class InformationActivity : AppCompatActivity(),
    InformationContract.View, GestureDetector.OnGestureListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var mPresenter: InformationPresenter
    private lateinit var gestureScanner: GestureDetector
    private lateinit var coinInformation: String
    private lateinit var myBundle: Bundle
    private lateinit var fragmentList: ArrayList<Fragment?>

    private var catches = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_information)
        supportActionBar?.hide()
        inforContext = this
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
                    viewFragmentMain()
                }
                R.id.action_book -> {
                    viewFragmentOrderBook()
                }
                R.id.action_detail -> {
                    viewFragmentDetails()
                }
                R.id.action_notification -> {
                    viewFragmentNotification()
                }
            }
            catches = item.itemId
        }
        return true
    }


    override fun changeUI() {
        myBundle = Bundle()
        myBundle.putString("symbol", coinInformation)
        myBundle.putSerializable("data",intent.getSerializableExtra("data")as CoinInfo)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(getNavi())
        window.navigationBarColor = resources.getColor(darkAndLightReverse())
        information_toolbar.background = resources.getDrawable(getNavi())
        information_title.text = coinInformation
        parent_information.background = resources.getDrawable(getTableOut())
        information_navi.background = resources.getDrawable(getBottom())
        information_navi.itemRippleColor =
            ContextCompat.getColorStateList(this@InformationActivity, rippleAndTintOfBottom())
        information_navi.itemIconTintList =
            ContextCompat.getColorStateList(this@InformationActivity, getBottomColor())
        information_navi.itemTextColor = ContextCompat.getColorStateList(this@InformationActivity, getBottomColor())
        toMainFromInformation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24, 0, 0, 0)
        initFragment()
    }

    override fun initFragment() {

        fragmentList = ArrayList()
        fragmentList.add(null)
        fragmentList.add(null)
        fragmentList.add(null)
        fragmentList.add(null)

        viewFragmentMain()
        catches = R.id.action_title

    }

    override fun viewFragmentMain() {
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

    override fun viewFragmentOrderBook() {
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

    override fun viewFragmentDetails() {
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

    override fun viewFragmentNotification() {
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
        inforContext = null
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
        if (e2!!.x - e1!!.x > com.wiffy.bitmexticker.ui.setting.SWIPE_MIN_DISTANCE && abs(velocityX) > com.wiffy.bitmexticker.ui.setting.SWIPE_THRESHOLD_VELOCITY) {
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

    fun setXBT(str:String){
        (fragmentList[0] as MainFragment).setXBT(str)
    }

    fun setPrice(str:String){
        (fragmentList[0] as MainFragment).setPrice(str)
    }
}
