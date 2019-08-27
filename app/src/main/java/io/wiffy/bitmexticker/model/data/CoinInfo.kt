package io.wiffy.bitmexticker.model.data

import java.io.Serializable


data class CoinInfo(
    val Symbol: String?,
    var price: String?,
    var is_new: String?,
    var name_info: String?,
    var before_p: String?,
    var chart_symbol: String?,
    var parse_str: String?
):Serializable
