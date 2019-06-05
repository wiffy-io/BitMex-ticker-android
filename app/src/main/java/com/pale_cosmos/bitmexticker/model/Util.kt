package com.pale_cosmos.bitmexticker.model

import android.content.SharedPreferences

class Util {

    companion object {
        //static으로 다크테마인지 라이트테마인지 저장
        var dark_theme : Boolean = true
        lateinit var sharedPreferences_theme:SharedPreferences
        lateinit var sharedPreferences_editor_theme:SharedPreferences.Editor
    }

}
