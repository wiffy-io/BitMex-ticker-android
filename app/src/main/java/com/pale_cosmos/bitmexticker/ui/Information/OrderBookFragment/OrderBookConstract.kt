package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

interface OrderBookConstract {
    interface View{
        fun changeUI()
        fun update_recycler(arr:ArrayList<OrderBook_info>)
        fun set_recycler()
    }
    interface Presenter{
        fun init()
        fun start_ws()
        fun stop_ws()
    }
}