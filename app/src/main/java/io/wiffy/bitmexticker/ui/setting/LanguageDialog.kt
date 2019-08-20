package io.wiffy.bitmexticker.ui.setting

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.restartApp
import io.wiffy.bitmexticker.function.setShared
import io.wiffy.bitmexticker.model.Component
import io.wiffy.bitmexticker.model.SuperContract
import kotlinx.android.synthetic.main.dialog_language.*
import java.util.*
import kotlin.collections.ArrayList

class LanguageDialog(context: Context, val mView: SettingContract.View) : SuperContract.SuperDialog(context) {
    var list = ArrayList<RelativeLayout>()
    var listRadio = ArrayList<AppCompatRadioButton>()
    var listString = ArrayList<String>()
    var ln = -1
    var lastCheck = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_language)
        with(list) {
            add(lang_EN)
            add(lang_KO)
            add(lang_ZH)
            add(lang_JA)
        }
        with(listRadio)
        {
            add(lang_EN[1] as AppCompatRadioButton)
            add(lang_KO[1] as AppCompatRadioButton)
            add(lang_ZH[1] as AppCompatRadioButton)
            add(lang_JA[1] as AppCompatRadioButton)
        }

        with(listString)
        {
            add("The system will be restarted!")
            add("시스템이 재시작됩니다!")
            add("系统将重新启动!")
            add("システムが再起動されます！")
        }

        ln =
            when (Component.global) {
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

        langSet.setTextColor(ContextCompat.getColorStateList(context, R.color.light_title))

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
                setShared("global", languages)

                restartApp(context)
            }
        }
        CANCEL.setOnClickListener {
            back()
        }
    }

    override fun onBackPressed() = back()

    private fun back() {
        dismiss()
    }

}