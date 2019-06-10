package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

interface OrderBookConstract {
    interface View{
        fun changeUI()
    }
    interface Presenter{
        fun init()
    }
}