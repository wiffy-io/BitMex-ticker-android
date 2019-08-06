package io.wiffy.bitmexticker.extension

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.R.attr.key
import android.content.SharedPreferences
import java.lang.Exception


lateinit var getShared: SharedPreferences

fun setShared(key:String,data:Any){
    val editor = getShared.edit()
    if (data is String){
        editor.putString(key, data)
    }
    else if (data is Boolean){
        editor.putBoolean(key, data)
    }
    else if (data is Float){
        editor.putFloat(key, data)
    }
    else if (data is Int){
        editor.putInt(key, data)
    }
    else if (data is Long){
        editor.putLong(key, data)
    }
    else{
        try {
            editor.putStringSet(key, data as Set<String>)
        }catch(e:Exception){ }
    }
    editor.commit()
}