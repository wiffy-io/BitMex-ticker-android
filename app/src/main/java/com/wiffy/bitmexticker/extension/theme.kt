package com.wiffy.bitmexticker.extension

import android.graphics.Color
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.model.Util


fun getTableIn(): Int {
    return if (Util.dark_theme) {
        R.color.dark_table_in
    } else {
        R.color.light_table_in
    }
}

fun getFragmentBackground(): Int {
    return if (Util.dark_theme) {
        R.color.dark_fragment_Background
    } else {
        R.color.light_table_out
    }
}

fun getTableOut(): Int {
    return if (Util.dark_theme) {
        R.color.dark_table_out
    } else {
        R.color.light_table_out
    }
}

fun getTableOutParsed(): Int {
    return if (Util.dark_theme) {
        Color.parseColor("#082335")
    } else {
        Color.parseColor("#efeff3")
    }
}

fun getTitle(): Int {
    return if (Util.dark_theme) {
        R.color.dark_title
    } else {
        R.color.light_title
    }
}

fun getTitle2(): Int {
    return if (Util.dark_theme) {
        R.color.dark_title2
    } else {
        R.color.light_title2
    }
}

fun getNavi(): Int {
    return if (Util.dark_theme) {
        R.color.dark_navi
    } else {
        R.color.light_navi
    }
}

fun getBrightness(): Int {
    return if (Util.dark_theme) {
        R.drawable.to_lights
    } else {
        R.drawable.to_darks
    }
}

fun getBottom(): Int {
    return if (Util.dark_theme) {
        R.color.dark_navi
    } else {
        R.color.WHITE
    }
}

fun getTableInReverse(): Int {
    return if (Util.dark_theme) {
        R.color.dark_table_in_click
    } else {
        R.color.light_table_in_click
    }
}

fun getBottomColor(): Int {
    return if (Util.dark_theme) {
        R.color.navigation_state_dark
    } else {
        R.color.navigation_state_light
    }
}

fun rippleAndTintOfBottom(): Int {
    return if (Util.dark_theme) {
        R.color.dark_table_out
    } else {
        R.color.light_navi
    }
}

fun darkAndLight(): Int {
    return if (Util.dark_theme) {
        R.color.WHITE
    } else {
        R.color.BLACK
    }
}

fun darkAndLightReverse(): Int {
    return if (Util.dark_theme) {
        R.color.BLACK
    } else {
        R.color.WHITE
    }
}

//fun detailsStateColor(): Int {
//    return if (Util.dark_theme) {
//        R.color.details_state_dark
//    } else {
//        R.color.details_state_light
//    }
//}

fun settingButton(): Int {
    return if (Util.dark_theme) {
        R.drawable.setting_button_dark_r
    } else {
        R.drawable.setting_button_light_r
    }
}

fun getDialog(): Int {
    return if (Util.dark_theme) {
        R.style.light_dialog
    } else {
        R.style.light_dialog
    }
}
