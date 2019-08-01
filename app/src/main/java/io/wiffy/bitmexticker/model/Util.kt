package io.wiffy.bitmexticker.model

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import io.wiffy.bitmexticker.ui.information.InformationActivity
import java.util.*
import kotlin.system.exitProcess

class Util {

    companion object {
        //static으로 다크테마인지 라이트테마인지 저장
        var dark_theme: Boolean = false
        var global: String? = "en"
        var infoContext: InformationActivity? = null

        lateinit var sharedPreferences: SharedPreferences
        lateinit var sharedPreferences_editor: SharedPreferences.Editor

        var info_on: Boolean = true
        var setting_on: Boolean = true
        var is_close: Boolean = false

        var noticom:Set<String>?=null

        @JvmStatic
        fun restartApp(context: Context) {
            context.startActivity(
                Intent.makeRestartActivityTask(
                    context.packageManager.getLaunchIntentForPackage(context.packageName)?.component
                )
            )
            exitProcess(0)
        }

        @JvmStatic
        fun wrap(context: Context?, language: String?): ContextWrapper? {
            val configuration = context?.resources?.configuration
            val newLocale = Locale(language)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration?.setLocale(newLocale)
                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)

            } else {
                configuration?.setLocale(newLocale)
            }
            return ContextWrapper(context?.createConfigurationContext(configuration!!))
        }
    }

}
