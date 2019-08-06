package io.wiffy.bitmexticker.ui.information.mainFragment

interface MainContract {
    interface View {
        fun changeUI()
        fun setChart(str: String)
        fun parseUI(coinBase: String, bitStamp: String): Boolean?
        fun parseCoinBase(str: String): String
        fun parseBitStamp(str: String): String
    }

    interface Presenter {
        fun init()
        fun makeChart()
        fun initParse()
        fun removeFlag()
    }
}