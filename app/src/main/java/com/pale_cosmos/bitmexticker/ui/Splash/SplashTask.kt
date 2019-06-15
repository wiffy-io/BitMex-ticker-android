package com.pale_cosmos.bitmexticker.ui.Splash

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class SplashTask(var view: Context, var presenter: SplashContract.Presenter) :
    AsyncTask<Void, Int, Boolean>() {

    val CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204"
    var InternetEnabled = true


    override fun doInBackground(vararg params: Void?): Boolean {

        var conn: HttpURLConnection? = null
        try {
            conn = URL(CONNECTION_CONFIRM_CLIENT_URL).openConnection() as HttpURLConnection
            conn.setRequestProperty("User-Agent", "Android")
            conn.connectTimeout = 1000
            conn.connect()
            InternetEnabled = when (conn.responseCode) {
                204 -> true
                else -> false
            }

        } catch (e: Exception) {
            InternetEnabled = false
        }
        conn?.disconnect()

        return InternetEnabled
    }

    override fun onPostExecute(result: Boolean?) {
        when (result) {
            true -> presenter.connectionOn()
            false -> presenter.connectionOff()
        }
    }
}