package io.wiffy.bitmexticker.model


import io.wiffy.bitmexticker.ui.information.InformationActivity
import java.util.*

object Component {

    var dark_theme: Boolean = false
    var global: String? = "en"
    var infoContext: InformationActivity? = null

    var info_on: Boolean = true
    var setting_on: Boolean = true
    var is_close: Boolean = false

    var width = 0

    var isConsumer = false

    var notificationSet: HashSet<String>? = null
}
