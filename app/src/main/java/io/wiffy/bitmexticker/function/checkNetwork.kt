package io.wiffy.bitmexticker.function

import android.content.Context;
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager;
import java.lang.Exception

fun getConnectivityStatus(context: Context) = try {
    (context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null
} catch (e: Exception) {
    false
}