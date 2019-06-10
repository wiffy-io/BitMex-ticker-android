package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pale_cosmos.bitmexticker.R

class OrderBookFragment: Fragment(),OrderBookConstract.View{
    lateinit var myView:View
    lateinit var mPresenter:OrderBookPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_orderbook, container,false)

        return myView
    }
}