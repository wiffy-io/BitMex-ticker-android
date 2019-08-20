package io.wiffy.bitmexticker.function

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@SuppressLint("SimpleDateFormat")
fun getTimeFormat(format: String): String = SimpleDateFormat(format).format(Date())


fun dpToPx(context: Context, dp: Int) = (dp * context.resources.displayMetrics.density).roundToInt()


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