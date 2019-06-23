package io.wiffy.bitmexticker.ui.information.orderBookFragment

interface OrderBookConstract {
    interface View{
        fun changeUI()
        fun update_recycler(arr:ArrayList<OrderBookInfo>)
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