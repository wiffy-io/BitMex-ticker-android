package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_fragment_background
import com.pale_cosmos.bitmexticker.extension.get_table_out

class OrderBookFragment : Fragment(), OrderBookConstract.View {
    lateinit var myView: View
    lateinit var mPresenter: OrderBookPresenter
    lateinit var parentLayout: RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_orderbook, container, false)
        mPresenter = OrderBookPresenter(this)
        mPresenter.init()
        return myView
    }

    override fun changeUI() {
        parentLayout = myView.findViewById(R.id.orderbooks)
        parentLayout.background = resources.getDrawable(get_fragment_background())
    }
}