package io.wiffy.bitmexticker.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import io.wiffy.bitmexticker.function.getShared
import io.wiffy.bitmexticker.model.Component.mySharedPreference
import java.util.*

class MyApplication : Application() {

    @SuppressLint("CommitPrefEdits")
    override fun onCreate() {
        super.onCreate()
        mySharedPreference = getSharedPreferences("bitMEX", Context.MODE_PRIVATE)
        Component.global = getShared("global", Locale.ENGLISH.toLanguageTag())
        Component.dark_theme = getShared("mode")
        Component.notificationSet = getShared("notificationSet")
        Component.isConsumer = getShared("consumer", false)
        Component.canSubscribe = false
    }
}