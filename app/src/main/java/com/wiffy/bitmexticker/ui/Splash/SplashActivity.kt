package com.wiffy.bitmexticker.ui.Splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.Splash.SplashPresenter
import com.wiffy.bitmexticker.model.Util
import com.wiffy.bitmexticker.ui.Main.MainActivity
import es.dmoral.toasty.Toasty

class SplashActivity : AppCompatActivity(), SplashContract.View {
    lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setTheme(R.style.AppTheme_D)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        mPresenter = SplashPresenter(this, applicationContext)
        mPresenter.startPresent()
    }

    override fun moveToMain(str:String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("symbol",str)
        startActivity(intent)
        overridePendingTransition(R.anim.abc_fade_in, R.anim.not_move_activity)
        finish()
    }

    override fun getOut() {
        Handler(mainLooper).post{
            Toasty.warning(applicationContext, applicationContext.getString(R.string.InternetCheck), Toast.LENGTH_SHORT, true).show();
            finish()
        }
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    override fun agreement() {
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
                //Util.sharedPreferences_editor.putBoolean("agreement", true).commit()
                //mPresenter.checkInternetConnection()
            }
            404 -> {
                mPresenter.checkInternetConnection()
                //startActivityForResult(Intent(applicationContext, AgreementActivity::class.java), 1)
                //overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }
            else -> {
                //finish()
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