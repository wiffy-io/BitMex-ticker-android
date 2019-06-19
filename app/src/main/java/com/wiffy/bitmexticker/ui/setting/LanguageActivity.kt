package com.wiffy.bitmexticker.ui.setting

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.model.Util
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*
import kotlin.collections.ArrayList

class LanguageActivity : AppCompatActivity() {
    var list = ArrayList<RelativeLayout>()
    var listRadio = ArrayList<AppCompatRadioButton>()
    var listString = ArrayList<String>()
    var ln = -1
    var lastCheck = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_language)
        list.add(lang_EN)
        list.add(lang_KO)
        list.add(lang_ZH)
        list.add(lang_JA)
        listRadio.add(lang_EN[1] as AppCompatRadioButton)
        listRadio.add(lang_KO[1] as AppCompatRadioButton)
        listRadio.add(lang_ZH[1] as AppCompatRadioButton)
        listRadio.add(lang_JA[1] as AppCompatRadioButton)
        listString.add("The system will be restarted!")
        listString.add("시스템이 재시작됩니다!")
        listString.add("系统将重新启动!")
        listString.add("システムが再起動されます！")
        ln =
            when (Util.global) {
                Locale.KOREAN.toLanguageTag() -> {
                    1
                }
                Locale.CHINESE.toLanguageTag() -> {
                    2
                }
                Locale.JAPANESE.toLanguageTag() -> {
                    3
                }
                else -> {
                    0
                }
            }
        lastCheck = ln
        listRadio[ln].isChecked = true

        for (RL in 0 until list.size) {
            list[RL].setOnClickListener {
                for (RB in 0 until listRadio.size) {
                    listRadio[RB].isChecked = when (RL) {
                        RB -> {
                            when (RL) {
                                ln -> {
                                    (makeText[0] as TextView).text = ""
                                }
                                else -> {
                                    (makeText[0] as TextView).text = listString[RL]
                                }
                            }
                            true
                        }
                        else -> false
                    }
                }
                lastCheck = RL
            }
        }

        langSet.setTextColor(ContextCompat.getColorStateList(applicationContext, R.color.light_title))



        OK.setOnClickListener {
            if (lastCheck == ln) back()
            else {
                val languages =
                    when (lastCheck) {
                        1 -> {
                            Locale.KOREAN.toLanguageTag()
                        }
                        2 -> {
                            Locale.CHINESE.toLanguageTag()
                        }
                        3 -> {
                            Locale.JAPANESE.toLanguageTag()
                        }
                        else -> {
                            Locale.ENGLISH.toLanguageTag()
                        }
                    }
                Util.sharedPreferences_editor.putString(
                    "global",
                    languages
                ).commit()

                Util.restartApp(applicationContext)
            }
        }
        CANCEL.setOnClickListener {
            back()
        }
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
            back()
        }
        return true
    }

    private fun back() {
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