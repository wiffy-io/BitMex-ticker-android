package io.wiffy.bitmexticker.extension

import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.model.Util


fun getTableIn() = if (Util.dark_theme) {
    R.color.dark_table_in
} else {
    R.color.light_table_in
}

fun getFragmentBackground() =
    if (Util.dark_theme) {
        R.color.dark_fragment_Background
    } else {
        R.color.light_table_out
    }


fun getTableOut() =
    if (Util.dark_theme) {
        R.color.dark_table_out
    } else {
        R.color.light_table_out
    }

fun getTitle() = if (Util.dark_theme) {
    R.color.dark_title
} else {
    R.color.light_title
}


fun getTitle2() = if (Util.dark_theme) {
    R.color.dark_title2
} else {
    R.color.light_title2
}


fun getNavi() = if (Util.dark_theme) {
    R.color.dark_navi
} else {
    R.color.dark_navi
}


fun getBottom() = if (Util.dark_theme) {
    R.color.dark_navi
} else {
    R.color.WHITE
}


fun getTableInReverse() = if (Util.dark_theme) {
    R.color.dark_table_in_click
} else {
    R.color.light_table_in_click
}


fun getBottomColor() = if (Util.dark_theme) {
    R.color.navigation_state_dark
} else {
    R.color.navigation_state_light
}


fun rippleAndTintOfBottom() = if (Util.dark_theme) {
    R.color.dark_table_out
} else {
    R.color.dark_navi
}


fun darkAndLight() = if (Util.dark_theme) {
    R.color.WHITE
} else {
    R.color.BLACK
}


fun darkAndLightReverse() = if (Util.dark_theme) {
    R.color.BLACK
} else {
    R.color.WHITE
}


fun settingButton() = if (Util.dark_theme) {
    R.drawable.setting_button_dark_r
} else {
    R.drawable.setting_button_light_r
}


fun getDialog() = if (Util.dark_theme) {
    R.style.light_dialog
} else {
    R.style.light_dialog
}

