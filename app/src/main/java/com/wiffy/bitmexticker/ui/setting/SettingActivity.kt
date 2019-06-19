package com.wiffy.bitmexticker.ui.setting


import android.content.*
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.*
import com.wiffy.bitmexticker.model.Util
import com.wiffy.bitmexticker.model.Util.Companion.setting_on
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.app_bar_setting.*
import android.app.AlertDialog


const val SWIPE_MIN_DISTANCE = 120
const val SWIPE_MAX_OFF_PATH = 250
const val SWIPE_THRESHOLD_VELOCITY = 150

class SettingActivity : AppCompatActivity(), SettingContract.View, GestureDetector.OnGestureListener {
    lateinit var mPresenter: SettingPresenter
    lateinit var gestureScanner: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_setting)
        supportActionBar?.hide()

        gestureScanner = GestureDetector(this)
        mPresenter = SettingPresenter(this,applicationContext)
        mPresenter.changeUI()
    }

    override fun changeUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(getNavi())
        window.navigationBarColor = resources.getColor(darkAndLightReverse())
        toolbar_setting.background = resources.getDrawable(getNavi())
        parent_setting.background = resources.getDrawable(getTableOut())
        toMainFromSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24, 0, 0, 0)

        OpenSource.background = resources.getDrawable(settingButton())
        Version.background = resources.getDrawable(settingButton())
        Language.background = resources.getDrawable(settingButton())
        Review.background = resources.getDrawable(settingButton())
        Email.background = resources.getDrawable(settingButton())

        (OpenSource[0] as TextView).setTextColor(resources.getColor(com.wiffy.bitmexticker.extension.getTitle()))
        (Version[0] as TextView).setTextColor(resources.getColor(com.wiffy.bitmexticker.extension.getTitle()))
        (Language[0] as TextView).setTextColor(resources.getColor(com.wiffy.bitmexticker.extension.getTitle()))
        (Review[0] as TextView).setTextColor(resources.getColor(com.wiffy.bitmexticker.extension.getTitle()))
        (Email[0] as TextView).setTextColor(resources.getColor(com.wiffy.bitmexticker.extension.getTitle()))
    }

    override fun moveToMain() {
        setting_on = true
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
        if (e2!!.x - e1!!.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
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


    override fun addSettingButtonListener(
        listener1: View.OnClickListener,
        listener2: View.OnClickListener,
        listener3: View.OnClickListener,
        listener4: View.OnClickListener,
        listener5: View.OnClickListener
    ) {
        OpenSource.setOnClickListener(listener1)
        Version.setOnClickListener(listener2)
        Review.setOnClickListener(listener3)
        Email.setOnClickListener(listener4)
        Language.setOnClickListener(listener5)
    }

    override fun startDialog(title: String, context: String) {
//        val intents = Intent(this@SettingActivity,DialogActivity::class.java)
//        intents.putExtra("TITLE",title)
//        intents.putExtra("CONTEXT",context)
//        startActivity(intents)
        val builder = AlertDialog.Builder(this,getDialog())
        builder.setTitle(title)
        builder.setMessage(context)
        builder.setPositiveButton(
            "OK"
        ) { _, _ ->  }
        builder.show()
    }

    override fun getStringTo(id: Int): String {
        return getString(id)
    }

    override fun clipOnBoard(clipBoardMessage: String) {
        val clip = applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("", clipBoardMessage)
        clip.primaryClip = clipData
    }

    override fun urlParseToMarket(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$url")))
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    override fun openLanguageSetting() {
        startActivity(Intent(applicationContext,LanguageActivity::class.java))
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
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