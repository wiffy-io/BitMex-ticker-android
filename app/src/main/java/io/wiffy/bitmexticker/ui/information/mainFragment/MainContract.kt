package io.wiffy.bitmexticker.ui.information.mainFragment

import io.wiffy.bitmexticker.model.SuperContract

interface MainContract {
    abstract class View : SuperContract.SuperFragment() {
        abstract fun changeUI()
        abstract fun setChart(str: String)
        abstract fun parseUI(coinBase: String, bitStamp: String): Boolean?
        abstract fun parseCoinBase(str: String): String
        abstract fun parseBitStamp(str: String): String
    }

    interface Presenter : SuperContract.WiffyObject {
        fun init()
        fun makeChart()
        fun initParse()
        fun removeFlag()
    }
}