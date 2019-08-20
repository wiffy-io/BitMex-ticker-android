package io.wiffy.bitmexticker.ui.splash


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.model.Component
import io.wiffy.bitmexticker.ui.main.MainActivity
import es.dmoral.toasty.Toasty
import io.wiffy.bitmexticker.function.getScreenSize
import io.wiffy.bitmexticker.function.getShared
import io.wiffy.bitmexticker.function.wrap

class SplashActivity : SplashContract.View() {
    lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setTheme(R.style.AppTheme_D)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Component.width = getScreenSize(this@SplashActivity).x
        mPresenter = SplashPresenter(this, applicationContext)
        agreement()
    }

    override fun moveToMain(str: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("symbol", str)
        startActivity(intent)
        overridePendingTransition(R.anim.abc_fade_in, R.anim.not_move_activity)
        finish()
    }

    override fun getOut() =
        Handler(mainLooper).post {
            Toasty.warning(
                applicationContext,
                resources.getString(R.string.InternetCheck),
                Toast.LENGTH_SHORT,
                true
            ).show()
            finish()
        }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    private fun agreement() {
        when (getShared("agreement", false)) {
            true -> {
                mPresenter.checkInternetConnection()
            }
            false -> {
                LanguageInitDialog(this@SplashActivity, this).apply {
                    setCancelable(false)
                }.show()
            }
        }
    }

    private fun setChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = getString(R.string.channel)
            val channelName = getString(R.string.app_name)
            val notiChannel =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelMessage =
                NotificationChannel(channel, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = ""
                    enableLights(true)
                    enableVibration(true)
                    setShowBadge(false)
                    vibrationPattern = addList()
                }
            notiChannel.createNotificationChannel(channelMessage)
        }
    }

    private fun addList() = LongArray(4).apply {
        this[0] = 100
        this[1] = 200
        this[2] = 100
        this[3] = 200
    }

    override fun resultOK() {
        setChannel()
        mPresenter.checkInternetConnection()
    }

    override fun resultCancel() = finish()

    override fun attachBaseContext(newBase: Context?) = super.attachBaseContext(
        wrap(
            newBase,
            Component.global
        )
    )

}