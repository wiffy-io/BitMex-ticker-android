package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_fragment_background
import com.pale_cosmos.bitmexticker.model.Util.Companion.dark_theme
import com.pale_cosmos.bitmexticker.ui.Information.InformationActivity
import kotlinx.android.synthetic.main.fragment_orderbook.*
import kotlinx.android.synthetic.main.fragment_orderbook.view.*

class OrderBookFragment : Fragment(), OrderBookConstract.View {
    lateinit var myView: View
    lateinit var mPresenter: OrderBookPresenter
    lateinit var parentLayout: RelativeLayout
    lateinit var recycler: RecyclerView

    var myAdapter:OrderBookAdapter?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_orderbook, container, false)
        recycler=myView.findViewById(R.id.orderbookRecycler)

        mPresenter = OrderBookPresenter(this)
        mPresenter.init()
        return myView
    }

    override fun changeUI() {
        parentLayout = myView.findViewById(R.id.orderbooks)
        parentLayout.background = resources.getDrawable(get_fragment_background())
        if(dark_theme){
            myView.orderbook_view.background=resources.getDrawable(R.drawable.chart_border_dark)
            myView.orderbook_large.background=resources.getDrawable(R.drawable.chart_border_dark)
        }else{
            myView.orderbook_view.background=resources.getDrawable(R.drawable.chart_border_light)
            myView.orderbook_large.background=resources.getDrawable(R.drawable.chart_border_light)
        }
    }

    override fun update_recycler(arr:ArrayList<OrderBook_info>) {
        myAdapter = OrderBookAdapter(
            arr,
            context!!,
            activity as InformationActivity
        )
        recycler.adapter = myAdapter
        recycler.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
    }

}