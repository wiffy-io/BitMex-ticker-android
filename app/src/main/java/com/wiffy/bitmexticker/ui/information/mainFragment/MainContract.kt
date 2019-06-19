package com.wiffy.bitmexticker.ui.information.mainFragment

interface MainContract {
    interface View{
        fun changeUI()
        fun setChart(str:String)
    }
    interface Presenter{
        fun init()
        fun makeChart()
    }
}