package com.pale_cosmos.bitmexticker.extension

fun change_value(d:Double): String{
    if (d > 1000){
        return String.format("%.1f", d)
    }else if (d > 10){
        return String.format("%.2f", d)
    }else{
        return String.format("%.8f", d)
    }
}