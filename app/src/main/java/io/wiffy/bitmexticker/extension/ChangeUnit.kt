package io.wiffy.bitmexticker.extension

import java.text.DecimalFormat

fun changeValue(d: Double) = when {
    d > 1000 -> String.format("%.1f", d)
    d > 10 && d <= 1000 -> String.format("%.2f", d)
    else -> String.format("%.8f", d)
}

fun inputComma(str: Double): String = DecimalFormat("#,###").format(str.toInt())
