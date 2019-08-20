package io.wiffy.bitmexticker.function

import android.content.Context;
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager;

fun getConnectivityStatus(context: Context): Boolean {
    val manager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo = manager.activeNetworkInfo
    if (networkInfo != null) {
        return true
    }
    return false
}
