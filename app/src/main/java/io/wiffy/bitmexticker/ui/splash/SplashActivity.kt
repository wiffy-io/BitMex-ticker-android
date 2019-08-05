package io.wiffy.bitmexticker.ui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.model.Util
import io.wiffy.bitmexticker.ui.main.MainActivity
import es.dmoral.toasty.Toasty

class SplashActivity : AppCompatActivity(), SplashContract.View {
    lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setTheme(R.style.AppTheme_D)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        mPresenter = SplashPresenter(this)
        mPresenter.startPresent()
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
            Activity.RESULT_OK -> mPresenter.checkInternetConnection()
            else -> finish()
        }
    }

    override fun attachBaseContext(newBase: Context?) = super.attachBaseContext(
        Util.wrap(
            newBase,
            Util.global
        )
    )


}