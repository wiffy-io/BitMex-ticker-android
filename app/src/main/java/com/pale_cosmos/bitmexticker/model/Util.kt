package com.pale_cosmos.bitmexticker.model

import android.content.SharedPreferences

class Util {

    companion object {
        //static으로 다크테마인지 라이트테마인지 저장
        var dark_theme : Boolean = true
        var global : String? = "en"

        lateinit var sharedPreferences:SharedPreferences
        lateinit var sharedPreferences_editor:SharedPreferences.Editor

        var info_on :Boolean = true
        var setting_on :Boolean = true
    }

}
