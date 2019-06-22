package io.wiffy.bitmexticker.ui.information.mainFragment

interface MainContract {
    interface View{
        fun changeUI()
        fun setChart(str:String)
        fun parseUI(coinbase:String, bitstamp:String)
        fun parseCoinbase(str: String):String
        fun parseBitstamp(str: String):String
    }
    interface Presenter{
        fun init()
        fun makeChart()
        fun initParse()
        fun removeFlag()
    }
}