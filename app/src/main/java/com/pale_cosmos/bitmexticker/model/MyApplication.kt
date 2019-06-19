package com.pale_cosmos.bitmexticker.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import java.util.*

class MyApplication : Application() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate() {
        super.onCreate()
        Util.sharedPreferences = getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        Util.sharedPreferences_editor = Util.sharedPreferences.edit() // editor를 static으로 선언함으로써 변경을 용이하게함
        Util.global = getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
            .getString("global", Locale.ENGLISH.toLanguageTag())
        Util.dark_theme = Util.sharedPreferences.getBoolean("mode", true)
    }
}