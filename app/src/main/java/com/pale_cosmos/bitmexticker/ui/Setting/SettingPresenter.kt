package com.pale_cosmos.bitmexticker.ui.Setting

import android.content.Context
import android.content.res.Configuration
import android.view.View
import androidx.core.content.ContextCompat
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_table_in
import com.pale_cosmos.bitmexticker.model.Util
import java.util.*

class SettingPresenter(act: SettingContract.View, cnt:Context) : SettingContract.Presenter {
    private val mView = act
    private val mContext = cnt

    override fun change_UI() {
        mView.changeUI()
        setSystemLanguage()
        mView.addTickerButtonListener(
            listener = View.OnClickListener {
                mView.moveToMain()
            })
        mView.addSettingButtonListener(
            listener1 = View.OnClickListener {
                mView.startDialog("OpenSource", mView.getStringTo(R.string.open_source))
            },
            listener2 = View.OnClickListener {
                mView.startDialog("Version", mView.getStringTo(R.string.version))
            },
            listener3 = View.OnClickListener {
                mView.urlParseToMarket(mView.getStringTo(R.string.store_url))
            },
            listener4 = View.OnClickListener {
                mView.clipOnBoard(mView.getStringTo(R.string.addmin_mail))
                mView.startDialog(mView.getStringTo(R.string.addmin_mail), "E-mail copied in clipboard")
            },
            listener5 = View.OnClickListener {
                mView.openLanguageSetting()
            })
    }
    override fun setSystemLanguage() {
        var config = Configuration()
        config.locale =  when (Util.global) {
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

        mContext.resources.updateConfiguration(config,mContext.resources.displayMetrics)
    }
}