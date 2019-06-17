package com.pale_cosmos.bitmexticker.ui.Splash

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.Util
import kotlinx.android.synthetic.main.language_init.*
import java.util.*

class LanguageInitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.language_init)

        lang_EN2.setOnClickListener {
            Util.sharedPreferences_editor.putString("global", Locale.ENGLISH.toLanguageTag()).apply()
            result404()
        }
        lang_KO2.setOnClickListener {
            Util.sharedPreferences_editor.putString("global", Locale.KOREAN.toLanguageTag()).apply()
            result404()
        }
        lang_ZH2.setOnClickListener {
            Util.sharedPreferences_editor.putString("global", Locale.CHINESE.toLanguageTag()).apply()
            result404()
        }
        lang_JA2.setOnClickListener {
            Util.sharedPreferences_editor.putString("global", Locale.JAPANESE.toLanguageTag()).apply()
            result404()
        }
    }



    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    override fun onBackPressed() {
        back()
    }

    private fun back() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

    private fun result404() {
        setResult(404)
        finish()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_OUTSIDE) {

        }
        return true
    }
}