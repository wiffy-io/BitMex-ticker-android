package io.wiffy.bitmexticker.ui.information

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.dmoral.toasty.Toasty
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.*
import io.wiffy.bitmexticker.model.CoinInfo
import io.wiffy.bitmexticker.model.Util
import io.wiffy.bitmexticker.model.Util.Companion.info_on
import io.wiffy.bitmexticker.model.Util.Companion.infoContext
import io.wiffy.bitmexticker.ui.information.detailsFragment.DetailsFragment
import io.wiffy.bitmexticker.ui.information.mainFragment.MainFragment
import io.wiffy.bitmexticker.ui.information.notificationFragment.NotificationFragment
import io.wiffy.bitmexticker.ui.information.orderBookFragment.OrderBookFragment
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
    private lateinit var coinInformationStructure: CoinInfo

    private var catches = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_information)
        supportActionBar?.hide()
        infoContext = this
        coinInformation = intent.getStringExtra("information")
        coinInformationStructure = intent.getSerializableExtra("data") as CoinInfo
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
                    if (coinInformationStructure.Symbol == "XBTUSD")
                        viewFragmentNotification()
                    else {
                        Handler(mainLooper).post {
                            Toasty.warning(
                                applicationContext,
                                resources.getString(R.string.NotificationWarning),
                                Toast.LENGTH_SHORT,
                                true
                            ).show()
                        }
                        return false
                    }
                }
            }
            catches = item.itemId
        }
        return true
    }


    override fun changeUI() {
        myBundle = Bundle().apply {
            putString("symbol", coinInformation)
            putString("xbt", intent.getStringExtra("xbt"))
            putSerializable("data", coinInformationStructure)
        }
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

        fragmentList = ArrayList<Fragment?>().apply {
            for (x in 0 until 4) add(null)
        }

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
        infoContext = null
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
        if (e2!!.x - e1!!.x > io.wiffy.bitmexticker.ui.setting.SWIPE_MIN_DISTANCE && abs(velocityX) > io.wiffy.bitmexticker.ui.setting.SWIPE_THRESHOLD_VELOCITY) {
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

    fun setXBT(str: String) {
        Handler(Looper.getMainLooper()).post {
            if (fragmentList[0] != null)
                (fragmentList[0] as MainFragment).setXBT(str)
            if (fragmentList[3] != null)
                (fragmentList[3] as NotificationFragment).setXBT(str)

        }
    }

    fun setPrice(str: String) {
        Handler(Looper.getMainLooper()).post {
            if (fragmentList[0] != null)
                (fragmentList[0] as MainFragment).setPrice(str)
            if (fragmentList[3] != null)
                (fragmentList[3] as NotificationFragment).setPrice(str)
        }
    }
}
