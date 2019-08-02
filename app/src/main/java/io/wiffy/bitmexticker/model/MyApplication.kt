package io.wiffy.bitmexticker.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
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
        Util.sharedPreferences = getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        with(Util.sharedPreferences) {
            Util.sharedPreferences_editor = this.edit()
            Util.global = this.getString("global", Locale.ENGLISH.toLanguageTag())
            Util.noticom = this.getStringSet("noticom", null)
            Util.dark_theme = this.getBoolean("mode", false)
        }
    }
}