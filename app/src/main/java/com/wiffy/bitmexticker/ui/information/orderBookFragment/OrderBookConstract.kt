package com.wiffy.bitmexticker.ui.information.orderBookFragment

interface OrderBookConstract {
    interface View{
        fun changeUI()
        fun update_recycler(arr:ArrayList<OrderBook_info>)
        fun set_recycler()
        fun start_loading()
        fun stop_loading()
        fun changeRecent(str:String)
    }
    interface Presenter{
        fun init()
        fun start_ws()
        fun stop_ws()
    }
}