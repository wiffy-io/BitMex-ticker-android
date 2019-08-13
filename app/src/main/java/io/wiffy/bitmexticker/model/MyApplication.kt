package io.wiffy.bitmexticker.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import io.wiffy.bitmexticker.extension.getShared
import io.wiffy.bitmexticker.extension.mySharedPreference
import java.net.URI
import java.util.*

class MyApplication : Application() {

    companion object {
        @JvmStatic
        var socket = BitMexSocket(URI("wss://www.bitmex.com/realtime"))
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate() {
        super.onCreate()
        mySharedPreference = getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        Util.global = getShared("global", Locale.ENGLISH.toLanguageTag())
        Util.dark_theme = getShared("mode")
        Util.notificationSet = getShared("notificationSet")
        Util.isConsumer = getShared("consumer", false)
    }
}