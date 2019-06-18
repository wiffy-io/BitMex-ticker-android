package com.pale_cosmos.bitmexticker.Splash

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.preference.PreferenceManager
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.ui.Main.MainActivity
import com.pale_cosmos.bitmexticker.ui.Splash.AgreementActivity
import com.pale_cosmos.bitmexticker.ui.Splash.LanguageInitActivity
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract
import java.util.*

class SplashActivity : AppCompatActivity(), SplashContract.View {
    lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        changeStatusBar()

        mPresenter = SplashPresenter(this, applicationContext)
        mPresenter.startPresent()

    }

    private fun changeStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.dark_table_out);
        window.navigationBarColor = resources.getColor(R.color.BLACK)
    }

    override fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.abc_fade_in, R.anim.not_move_activity)
        finish()
    }

    override fun getOut() {
        Toast.makeText(applicationContext, applicationContext.getString(R.string.InternetCheck), Toast.LENGTH_SHORT)
            .show()
        finish()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }

    }

    override fun argreement() {
        when (Util.sharedPreferences.getBoolean("agreement", false)) {
            true -> {
                mPresenter.checkInternetConnection()
            }
            false -> {
                startActivityForResult(Intent(applicationContext, LanguageInitActivity::class.java), 1)
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                Util.sharedPreferences_editor.putBoolean("agreement", true).commit()
                mPresenter.checkInternetConnection()
            }
            404 -> {
                startActivityForResult(Intent(applicationContext, AgreementActivity::class.java), 1)
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }
            else -> {
                finish()
            }
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