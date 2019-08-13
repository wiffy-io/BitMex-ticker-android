package io.wiffy.bitmexticker.ui.information.orderBookFragment

import io.wiffy.bitmexticker.ui.information.orderBookFragment.tool.OrderBookInfo

interface OrderBookConstract {
    interface View {
        fun changeUI()
        fun updateRecycler(arr: ArrayList<OrderBookInfo>, sum:Int): Boolean
        fun setRecycler(): Boolean
        fun startLoading()
        fun stopLoading()
        fun changeRecent(str: String)
    }

    interface Presenter {
        fun init()
        fun startWs()
        fun stopWs()
    }
}