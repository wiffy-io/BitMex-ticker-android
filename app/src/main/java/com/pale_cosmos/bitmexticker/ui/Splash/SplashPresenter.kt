package com.pale_cosmos.bitmexticker.Splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.ui.Splash.SplashContract
import com.pale_cosmos.bitmexticker.ui.Splash.SplashTask
import org.intellij.lang.annotations.Language
import java.util.*


class SplashPresenter(act: SplashContract.View, cnt: Context) : SplashContract.Presenter {

    private val mView = act
    private val mContext = cnt

    @SuppressLint("CommitPrefEdits")
    override fun startPresent() {
        Util.sharedPreferences = mContext.getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        Util.sharedPreferences_editor = Util.sharedPreferences.edit() // editor를 static으로 선언함으로써 변경을 용이하게함


        Util.dark_theme = Util.sharedPreferences.getBoolean("mode", true)
//        setSystemLanguage()
        mView.argreement()

    }

    override fun setSystemLanguage() {
        Util.global = Util.sharedPreferences.getString("global", Locale.ENGLISH.toLanguageTag())
        var config = Configuration()
        config.locale = when (Util.global) {
            Locale.KOREAN.toLanguageTag() -> {
                Locale.KOREAN
            }
            Locale.CHINESE.toLanguageTag() -> {
                Locale.CHINESE
            }
            Locale.JAPANESE.toLanguageTag() -> {
                Locale.JAPANESE
            }
            else -> {
                Locale.ENGLISH
            }
        }

        mContext.resources.updateConfiguration(config, mContext.resources.displayMetrics)
        checkInternetConnection()
    }

    override fun checkInternetConnection() {
        SplashTask(mContext, this).execute()
    }


    override fun connectionOn() {
        Handler(Looper.getMainLooper()).postDelayed({
            mView.moveToMain()
        }, 650)
    }

    override fun connectionOff() {
        mView.getOut()
    }


}