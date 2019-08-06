@file:Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")

package io.wiffy.bitmexticker.extension

import android.content.SharedPreferences

lateinit var getShared: SharedPreferences

inline fun <reified T> setShared(key: String, data: T) = getShared.edit().apply {
    when (T::class) {
        String::class -> putString(key, data as String)
        Boolean::class -> putBoolean(key, data as Boolean)
        Float::class -> putFloat(key, data as Float)
        Int::class -> putInt(key, data as Int)
        Long::class -> putLong(key, data as Long)
        Set::class -> putStringSet(key, data as Set<String>)
    }
}.commit()

inline fun <reified T> getShared(key: String): T? = getShared.run {
    when (T::class) {
        String::class -> getString(key, null)
        Boolean::class -> getBoolean(key, false)
        Float::class -> getFloat(key, 0.0f)
        Int::class -> getInt(key, 0)
        Long::class -> getLong(key, 0L)
        Set::class -> getStringSet(key, null)
        else -> ""
    } as T?
}
