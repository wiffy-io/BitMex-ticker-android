package io.wiffy.bitmexticker.ui.information.orderBookFragment

import androidx.fragment.app.Fragment
import io.wiffy.bitmexticker.ui.information.orderBookFragment.tool.OrderBookInfo

interface OrderBookContract {
    abstract class View : Fragment() {
        abstract fun changeUI()
        abstract fun updateRecycler(arr: ArrayList<OrderBookInfo>, sum: Int): Boolean
        abstract fun setRecycler(): Boolean
        abstract fun startLoading()
        abstract fun stopLoading()
        abstract fun changeRecent(str: String)
    }

    interface Presenter {
        fun init()
        fun startWs()
        fun stopWs()
    }
}