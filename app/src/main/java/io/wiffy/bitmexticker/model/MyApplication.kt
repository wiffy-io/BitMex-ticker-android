package io.wiffy.bitmexticker.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import java.net.URI
import java.util.*

class MyApplication : Application() {

    companion object {
        @JvmStatic
        var socket = BitMEX_soket(URI("wss://www.bitmex.com/realtime"))
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate() {
        super.onCreate()
        Util.sharedPreferences = getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        Util.sharedPreferences_editor = Util.sharedPreferences.edit() // editor를 static으로 선언함으로써 변경을 용이하게함
        Util.global = Util.sharedPreferences
            .getString("global", Locale.ENGLISH.toLanguageTag())
        Util.dark_theme = Util.sharedPreferences.getBoolean("mode", false)
    }
}