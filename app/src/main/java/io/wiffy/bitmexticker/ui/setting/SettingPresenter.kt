package io.wiffy.bitmexticker.ui.setting

import android.content.Context
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.CompoundButton
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.setShared
import io.wiffy.bitmexticker.model.Component
import java.util.*
import android.content.pm.PackageManager
import android.R.attr.versionName
import android.content.Intent
import android.content.pm.PackageInfo
import androidx.browser.customtabs.CustomTabsClient.getPackageName
import io.wiffy.bitmexticker.BuildConfig
import io.wiffy.bitmexticker.ui.subscription.SubscriptionActivity


class SettingPresenter(private val mView: SettingContract.View, private val mContext: Context) :
    SettingContract.Presenter {

    override fun changeUI() {
        mView.changeUI()
        setSystemLanguage()
        mView.addTickerButtonListener(
            listener = View.OnClickListener {
                mView.moveToMain()
            })
        mView.addSettingButtonListener(
            listener1 = View.OnClickListener {
                mView.startDialog(
                    "OpenSource",
                    "Java-WebSocket\nhttps://github.com/TooTallNate/Java-WebSocket\n\n" +
                            "Toasty\nhttps://github.com/GrenderG/Toasty\n\n" +
                            "AVLoadingIndicatorView\nhttps://github.com/81813780/AVLoadingIndicatorView\n\n" +
                            "Jsoup\nhttps://github.com/jhy/jsoup"
                )
            },
            listener2 = View.OnClickListener {
                try {
                    val tmp = mContext.packageManager.getPackageInfo(mContext.packageName, 0)
                    val versionC = tmp.versionName
                    val versionN = tmp.versionCode
                    mView.startDialog("Version", "${versionC} (${versionN})")
                } catch (e: Exception) {
                    mView.startDialog("Version", "")
                }
            },
            listener3 = View.OnClickListener {
                mView.urlParseToMarket(mView.getStringTo(R.string.store_url))
            },
            listener4 = View.OnClickListener {
                mView.clipOnBoard(mView.getStringTo(R.string.admin_mail))
                mView.startDialog(
                    mView.getStringTo(R.string.admin_mail),
                    "E-mail copied in clipboard"
                )
            },
            listener5 = View.OnClickListener {
                mView.openLanguageSetting()
            },
            listener6 = CompoundButton.OnCheckedChangeListener { switch, isChecked ->
                when (isChecked) {
                    false -> {
                        setShared("mode", true)
                        Component.dark_theme = true
                        switch.isChecked = true
                    }
                    true -> {
                        setShared("mode", false)
                        Component.dark_theme = false
                        switch.isChecked = false
                    }
                }
                mView.changeUI()
            },
            listener7 = View.OnClickListener {
            })
    }

    override fun setSystemLanguage() = Handler(Looper.getMainLooper()).post {
        mContext.resources.updateConfiguration(Configuration().apply {
            locale = when (Component.global) {
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
        }, mContext.resources.displayMetrics)
    }

}