package com.pale_cosmos.bitmexticker.ui.Setting


import android.app.Dialog
import android.content.*
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.*
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.model.Util.Companion.setting_on
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.app_bar_setting.*


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

        gestureScanner = GestureDetector(this)
        mPresenter = SettingPresenter(this,applicationContext)
        mPresenter.change_UI()
    }

    override fun changeUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(get_navi())
        window.navigationBarColor = resources.getColor(darkAndLight_reverse())
        toolbar_setting.background = resources.getDrawable(get_navi())
        parent_setting.background = resources.getDrawable(get_table_out())
        toMainFromSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24, 0, 0, 0)

        OpenSource.background = resources.getDrawable(setting_button())
        Version.background = resources.getDrawable(setting_button())
        Language.background = resources.getDrawable(setting_button())
        Review.background = resources.getDrawable(setting_button())
        Email.background = resources.getDrawable(setting_button())

        (OpenSource[0] as TextView).setTextColor(resources.getColor(get_title()))
        (Version[0] as TextView).setTextColor(resources.getColor(get_title()))
        (Language[0] as TextView).setTextColor(resources.getColor(get_title()))
        (Review[0] as TextView).setTextColor(resources.getColor(get_title()))
        (Email[0] as TextView).setTextColor(resources.getColor(get_title()))
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

        var builder = Dialog(this@SettingActivity)
        builder.setContentView(R.layout.activity_dialog)
        builder.setCancelable(false)
        builder.setCanceledOnTouchOutside(false)
        builder.setOnShowListener {
            var x = builder.findViewById<Button>(R.id.OKBUTTON)
            x.setOnClickListener {
                builder.dismiss()
            }
            var y = builder.findViewById<TextView>(R.id.contextInDialog)
            y.text = context
            var z = builder.findViewById<TextView>(R.id.titleInDialog)
            z.text = title
        }
        builder.window?.setBackgroundDrawableResource(android.R.color.transparent)
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
}