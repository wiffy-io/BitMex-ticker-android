package io.wiffy.bitmexticker.ui.setting

import android.content.Context
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.CompoundButton
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.setShared
import io.wiffy.bitmexticker.model.Util
import java.util.*

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
                mView.startDialog("Version", mView.getStringTo(R.string.version))
            },
            listener3 = View.OnClickListener {
                mView.urlParseToMarket(mView.getStringTo(R.string.store_url))
            },
            listener4 = View.OnClickListener {
                mView.clipOnBoard(mView.getStringTo(R.string.admin_mail))
                mView.startDialog(mView.getStringTo(R.string.admin_mail), "E-mail copied in clipboard")
            },
            listener5 = View.OnClickListener {
                mView.openLanguageSetting()
            },
            listener6 = CompoundButton.OnCheckedChangeListener { switch, isChecked ->
                when (isChecked) {
                    false -> {
                        setShared("mode", true)
                        Util.dark_theme = true
                        switch.isChecked = true
                    }
                    true -> {
                        setShared("mode", false)
                        Util.dark_theme = false
                        switch.isChecked = false
                    }
                }
                mView.changeUI()
            })
    }

    override fun setSystemLanguage() = Handler(Looper.getMainLooper()).post {
        val config = Configuration()
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
    }

}