package io.wiffy.bitmexticker.ui.information.orderBookFragment

import io.wiffy.bitmexticker.model.SuperContract
import io.wiffy.bitmexticker.ui.information.orderBookFragment.tool.OrderBookInfo

interface OrderBookContract {
    abstract class View : SuperContract.SuperFragment() {
        abstract fun changeUI()
        abstract fun updateRecycler(arr: ArrayList<OrderBookInfo>, sum: Int): Boolean
        abstract fun setRecycler(): Boolean
        abstract fun startLoading()
        abstract fun stopLoading()
        abstract fun changeRecent(str: String)
    }

    interface Presenter : SuperContract.WiffyObject {
        fun init()
        fun startWs()
        fun stopWs()
    }
}