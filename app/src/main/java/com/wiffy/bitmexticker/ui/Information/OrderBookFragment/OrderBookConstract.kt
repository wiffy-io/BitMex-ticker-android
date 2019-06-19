package com.wiffy.bitmexticker.ui.Information.OrderBookFragment

interface OrderBookConstract {
    interface View{
        fun changeUI()
        fun update_recycler(arr:ArrayList<OrderBook_info>)
        fun set_recycler()
        fun start_loading()
        fun stop_loading()
    }
    interface Presenter{
        fun init()
        fun start_ws()
        fun stop_ws()
    }
}