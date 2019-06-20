package com.wiffy.bitmexticker.ui.information.mainFragment

interface MainContract {
    interface View{
        fun changeUI()
        fun setChart(str:String)
        fun parseUI(coinbase:String, bitstamp:String)
    }
    interface Presenter{
        fun init()
        fun makeChart()
        fun initParse()
        fun removeFlag()
    }
}