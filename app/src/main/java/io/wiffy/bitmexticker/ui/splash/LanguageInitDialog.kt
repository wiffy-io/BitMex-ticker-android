package io.wiffy.bitmexticker.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.Window
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.setShared
import io.wiffy.bitmexticker.model.Component
import io.wiffy.bitmexticker.model.SuperContract
import kotlinx.android.synthetic.main.dialog_language_init.*
import java.util.*

class LanguageInitDialog(context: Context, val mView: SplashContract.View) :
    SuperContract.SuperDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_language_init)

        lang_EN2.setOnClickListener {
            onClickListener(Locale.ENGLISH.toLanguageTag())
        }
        lang_KO2.setOnClickListener {
            onClickListener(Locale.KOREAN.toLanguageTag())
        }
        lang_ZH2.setOnClickListener {
            onClickListener(Locale.CHINESE.toLanguageTag())
        }
        lang_JA2.setOnClickListener {
            onClickListener(Locale.JAPANESE.toLanguageTag())
        }
    }

    private fun onClickListener(locale: String) {
        dismiss()
        setShared("global", locale)
        Component.global = locale
        resultOK()
    }

    override fun onBackPressed() = mView.resultCancel()

    private fun resultOK() = mView.resultOK()
}