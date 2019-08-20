@file:Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")

package io.wiffy.bitmexticker.function

import io.wiffy.bitmexticker.model.Component.mySharedPreference


inline fun <reified T> setShared(key: String, data: T) = mySharedPreference.edit().apply {
    when (T::class) {
        String::class -> putString(key, data as String)
        Boolean::class -> putBoolean(key, data as Boolean)
        Float::class -> putFloat(key, data as Float)
        Int::class -> putInt(key, data as Int)
        Long::class -> putLong(key, data as Long)
        HashSet::class -> putStringSet(key, data as HashSet<String>)
    }
}.commit()

inline fun <reified T> getShared(key: String): T = mySharedPreference.run {
    when (T::class) {
        String::class -> getString(key, "")
        Boolean::class -> getBoolean(key, false)
        Float::class -> getFloat(key, 0.0f)
        Int::class -> getInt(key, 0)
        Long::class -> getLong(key, 0L)
        HashSet::class -> getStringSet(key, HashSet<String>())
        else -> ""
    } as T
}

inline fun <reified T> getShared(key: String, default: T): T = mySharedPreference.run {
    when (T::class) {
        String::class -> getString(key, default as String)
        Boolean::class -> getBoolean(key, default as Boolean)
        Float::class -> getFloat(key, default as Float)
        Int::class -> getInt(key, default as Int)
        Long::class -> getLong(key, default as Long)
        HashSet::class -> getStringSet(key, default as HashSet<String>)
        else -> ""
    } as T
}

fun removeShared(key: String) = mySharedPreference.edit().apply {
    remove(key)
}.commit()