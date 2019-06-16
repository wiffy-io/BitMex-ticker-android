package com.pale_cosmos.bitmexticker.extension

import android.graphics.Color
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.Util


fun get_table_in(): Int {
    if (Util.dark_theme)
        return R.color.dark_table_in
    else return R.color.light_table_in
}

fun get_fragment_background():Int{
    if(Util.dark_theme)
        return R.color.dark_fragment_Background
    else return R.color.light_table_out
}

fun get_table_out(): Int {
    if (Util.dark_theme)
        return R.color.dark_table_out
    else return R.color.light_table_out
}

fun get_table_out_parsed():Int{
    if (Util.dark_theme)
        return Color.parseColor("#082335")
    else return Color.parseColor("#efeff3")
}

fun get_title(): Int {
    if (Util.dark_theme)
        return R.color.dark_title
    else return R.color.light_title
}

fun get_title2(): Int {
    if (Util.dark_theme)
        return R.color.dark_title2
    else return R.color.light_title2
}

fun get_navi(): Int {
    if (Util.dark_theme)
        return R.color.dark_navi
    else return R.color.light_navi
}

fun get_brightness(): Int {
    if (Util.dark_theme)
        return R.drawable.to_lights
    else return R.drawable.to_darks
}

fun get_bottom():Int{
    if(Util.dark_theme)return R.color.dark_navi
    else return R.color.WHITE
}

fun get_table_in_reverse():Int{
    if (Util.dark_theme)
        return R.color.dark_table_in_click
    else return R.color.light_table_in_click
}

fun get_bottom_color():Int{
    if(Util.dark_theme)
        return R.color.navigation_state_dark
    else return R.color.navigation_state_light
}

fun rippleAndTintOfBottom():Int
{
    if(Util.dark_theme)
        return R.color.dark_table_out
    else return R.color.light_navi
}

fun darkAndLight():Int
{
    if(Util.dark_theme)
        return R.color.WHITE
    else return R.color.BLACK
}

fun darkAndLight_reverse():Int
{
    if(Util.dark_theme)
        return R.color.BLACK
    else return R.color.WHITE
}

fun details_state_color():Int
{
    if(Util.dark_theme)
        return R.color.details_state_dark
    else return R.color.details_state_light
}

fun setting_button():Int
{
    if(Util.dark_theme)
        return R.drawable.setting_button_dark_r
    else return R.drawable.setting_button_light_r
}
