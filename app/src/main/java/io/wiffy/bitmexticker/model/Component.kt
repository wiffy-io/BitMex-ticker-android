package io.wiffy.bitmexticker.model


import android.content.SharedPreferences
import io.wiffy.bitmexticker.ui.information.InformationActivity
import java.util.*

object Component {

    lateinit var mySharedPreference: SharedPreferences

    var canSubscribe = false

    var dark_theme: Boolean = false
    var global: String? = "en"
    var infoContext: InformationActivity? = null

    var orderCount = 20

    var info_on: Boolean = true
    var setting_on: Boolean = true
    var subscription_on: Boolean = true
    var is_close: Boolean = false

    var width = 0

    /** 이게 구매자인지 아닌지 판가름 */
    var isConsumer = false

    var notificationSet: HashSet<String>? = null
}
