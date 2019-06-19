package com.wiffy.bitmexticker.bin

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.model.Util


class AgreementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        setContentView(R.layout.activity_agreement)
//        OK_d.setOnClickListener { result_OK() }
//        CANCEL_d.setOnClickListener { back() }
    }

    override fun onBackPressed() {
        back()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_OUTSIDE) {

        }
        return true
    }

    private fun back() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

    private fun result_OK() {
        setResult(Activity.RESULT_OK)
        finish()
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