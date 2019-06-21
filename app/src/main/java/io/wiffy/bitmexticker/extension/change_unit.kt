package io.wiffy.bitmexticker.extension

fun changeValue(d: Double): String {
    return when {
        d > 1000 -> String.format("%.1f", d)
        d > 10 && d <= 1000 -> String.format("%.2f", d)
        else -> String.format("%.8f", d)
    }
}
