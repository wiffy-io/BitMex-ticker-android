package com.pale_cosmos.bitmexticker.extension

import java.net.HttpURLConnection
import java.net.URL

fun String.getUrlText(): String {
    return URL(this).run {
        openConnection().run {
            this as HttpURLConnection
            inputStream.bufferedReader().readText()
        }
    }
}
