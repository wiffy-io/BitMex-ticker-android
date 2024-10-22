package io.wiffy.bitmexticker.ui.setting


import android.annotation.SuppressLint
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
import androidx.core.view.get
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.*
import io.wiffy.bitmexticker.model.Component
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.app_bar_setting.*
import android.app.AlertDialog
import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat
import io.wiffy.bitmexticker.model.Component.setting_on
import io.wiffy.bitmexticker.function.SWIPE_MIN_DISTANCE
import io.wiffy.bitmexticker.function.SWIPE_THRESHOLD_VELOCITY
import io.wiffy.bitmexticker.ui.subscription.SubscriptionActivity
import kotlin.math.abs


class SettingActivity : SettingContract.View() {
    private lateinit var mPresenter: SettingPresenter
    private lateinit var gestureScanner: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_setting)
        supportActionBar?.hide()

        gestureScanner = GestureDetector(this)
        mPresenter = SettingPresenter(this, applicationContext)
        mPresenter.changeUI()
    }

    @SuppressLint("SetTextI18n")
    override fun changeUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        with(resources, {
            window.statusBarColor = getColor(getNavi())
            window.navigationBarColor = getColor(darkAndLightReverse())
            toolbar_setting.background = getDrawable(getNavi())
            parent_setting.background = getDrawable(getTableOut())
            toMainFromSetting.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.baseline_chevron_left_24,
                0,
                0,
                0
            )
            OpenSource.background = getDrawable(settingButton())
            Version.background = getDrawable(settingButton())
            Language.background = getDrawable(settingButton())
            Review.background = getDrawable(settingButton())
            Email.background = getDrawable(settingButton())
            Theme.background = getDrawable(settingButton())
            gopro.background = getDrawable(settingButton())
            (Theme[1] as SwitchCompat).isChecked = Component.dark_theme xor true
            (OpenSource[0] as TextView).setTextColor(getColor(io.wiffy.bitmexticker.function.getTitle()))
            (Version[0] as TextView).setTextColor(getColor(io.wiffy.bitmexticker.function.getTitle()))
            (Language[0] as TextView).setTextColor(getColor(io.wiffy.bitmexticker.function.getTitle()))
            (Review[0] as TextView).setTextColor(getColor(io.wiffy.bitmexticker.function.getTitle()))
            (Email[0] as TextView).setTextColor(getColor(io.wiffy.bitmexticker.function.getTitle()))
            (Theme[0] as TextView).setTextColor(getColor(io.wiffy.bitmexticker.function.getTitle()))
            (gopro[0] as TextView).setTextColor(getColor(io.wiffy.bitmexticker.function.getTitle()))
            if (Component.isConsumer) {
                (gopro[0] as TextView).text = "MANGE PRO"
            }
        })
    }

    override fun moveToMain() {
        setting_on = true
        finish()
        overridePendingTransition(R.anim.leftin_activity, R.anim.rightout_activity)
    }

    override fun addTickerButtonListener(listener: View.OnClickListener) =
        toMainFromSetting.setOnClickListener(listener)


    override fun onBackPressed() = moveToMain()


    override fun onTouchEvent(event: MotionEvent?) = gestureScanner.onTouchEvent(event)


    override fun onDown(e: MotionEvent?) = true

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e2!!.x - e1!!.x > SWIPE_MIN_DISTANCE && abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            moveToMain()
        }
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float) =
        true


    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?) = true


    override fun addSettingButtonListener(
        listener1: View.OnClickListener,
        listener2: View.OnClickListener,
        listener3: View.OnClickListener,
        listener4: View.OnClickListener,
        listener5: View.OnClickListener,
        listener6: CompoundButton.OnCheckedChangeListener,
        listener7: View.OnClickListener
    ) {
        OpenSource.setOnClickListener(listener1)
        Version.setOnClickListener(listener2)
        Review.setOnClickListener(listener3)
        Email.setOnClickListener(listener4)
        Language.setOnClickListener(listener5)
        (Theme[1] as SwitchCompat).setOnCheckedChangeListener(listener6)
        gopro.setOnClickListener {
            if (Component.subscription_on) {
                Component.subscription_on = false
                startActivity(Intent(this@SettingActivity, SubscriptionActivity::class.java))
                overridePendingTransition(R.anim.rightin_activity, R.anim.leftout_activity)
            }
        }
    }

    override fun startDialog(title: String, context: String): AlertDialog =
        AlertDialog.Builder(this, getDialog()).apply {
            setTitle(title)
            setMessage(context)
            setPositiveButton(
                "OK"
            ) { _, _ -> }
        }.show()

    override fun getStringTo(id: Int): String = getString(id)

    override fun clipOnBoard(clipBoardMessage: String) =
        (applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).apply {
            setPrimaryClip(ClipData.newPlainText("", clipBoardMessage))
        }

    override fun urlParseToMarket(url: String) =
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$url")))


    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    override fun openLanguageSetting() = LanguageDialog(this@SettingActivity).apply {
        setCancelable(false)
    }.show()


    override fun attachBaseContext(newBase: Context?) = super.attachBaseContext(
        wrap(
            newBase,
            Component.global
        )
    )

}
