package io.wiffy.bitmexticker.ui.information

import android.content.Context
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.view.View
import io.wiffy.bitmexticker.model.Util
import java.util.*

class InformationPresenter(act: InformationContract.View, cnt: Context) : InformationContract.Presenter {
    private val mContext = cnt
    private val mView = act
    override fun init() {
        mView.changeUI()
        mView.addTickerButtonListener(listener = View.OnClickListener {
            mView.moveToMain()
        })
    }

    override fun setSystemLanguage() {
        Handler(Looper.getMainLooper()).post {
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
}