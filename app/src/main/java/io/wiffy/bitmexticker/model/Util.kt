package io.wiffy.bitmexticker.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.os.LocaleList
import com.google.firebase.messaging.FirebaseMessaging
import io.wiffy.bitmexticker.extension.removeShared
import io.wiffy.bitmexticker.extension.setShared
import io.wiffy.bitmexticker.ui.information.InformationActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.system.exitProcess

class Util {

    companion object {

        var dark_theme: Boolean = false
        var global: String? = "en"
        var infoContext: InformationActivity? = null

        var info_on: Boolean = true
        var setting_on: Boolean = true
        var is_close: Boolean = false

        var isConsumer = false

        var notificationSet: HashSet<String>? = null

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
        fun cleanNotificationSubscribe() {
            notificationSet?.apply {
                for (slice in iterator()) {
                    val cheese = slice.split(":")
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("${cheese[0]}_${cheese[1]}")
                }
            }?.clear()
            removeShared("notificationSet")
        }

        @JvmStatic
        fun beConsumer(){
            isConsumer = true
            setShared("consumer", true)
        }

        @SuppressLint("SimpleDateFormat")
        @JvmStatic
        fun getTimeFormat(format: String):String = SimpleDateFormat(format).format(Date())

        @JvmStatic
        fun dpToPx(context: Context, dp: Int) = (dp * context.resources.displayMetrics.density).roundToInt()

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
