package io.wiffy.bitmexticker.function

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import com.google.firebase.messaging.FirebaseMessaging
import io.wiffy.bitmexticker.model.Component
import kotlin.system.exitProcess

fun restartApp(context: Context) {
    context.startActivity(
        Intent.makeRestartActivityTask(
            context.packageManager.getLaunchIntentForPackage(context.packageName)?.component
        )
    )
    exitProcess(0)
}

fun cleanNotificationSubscribe() {
    Component.notificationSet?.apply {
        for (slice in iterator()) {
            val cheese = slice.split(":")
            FirebaseMessaging.getInstance().unsubscribeFromTopic("${cheese[0]}_${cheese[1]}")
        }
    }?.clear()
    removeShared("notificationSet")
}

fun getScreenSize(activity: Activity): Point =
    Point().apply {
        activity.windowManager.defaultDisplay.getSize(this)
    }


fun beConsumer() {
    Component.isConsumer = true
    setShared("consumer", true)
}