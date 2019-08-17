package io.wiffy.bitmexticker.ui.information.mainFragment

import androidx.fragment.app.Fragment

interface MainContract {
    abstract class View : Fragment() {
        abstract fun changeUI()
        abstract fun setChart(str: String)
        abstract fun parseUI(coinBase: String, bitStamp: String): Boolean?
        abstract fun parseCoinBase(str: String): String
        abstract fun parseBitStamp(str: String): String
    }

    interface Presenter {
        fun init()
        fun makeChart()
        fun initParse()
        fun removeFlag()
    }
}