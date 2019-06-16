package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import java.lang.Exception

class OrderBookFragment : Fragment(), OrderBookConstract.View {
    lateinit var myView: View
    lateinit var mPresenter: OrderBookPresenter
    var builder: Dialog? = null
    var myAdapter: OrderBookAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_orderbook, container, false)

        init_loading()
        val sym = arguments?.getString("symbol")!!
        mPresenter = OrderBookPresenter(this, sym)
        mPresenter.init()

        return myView
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start_ws()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.stop_ws()
    }

    override fun changeUI() {
        myView.orderbooks.background = resources.getDrawable(get_fragment_background())
        if (dark_theme) {
            myView.orderbook_view.background = resources.getDrawable(R.drawable.chart_border_dark)
            myView.orderbook_large.background = resources.getDrawable(R.drawable.chart_border_dark)
        } else {
            myView.orderbook_view.background = resources.getDrawable(R.drawable.chart_border_light)
            myView.orderbook_large.background = resources.getDrawable(R.drawable.chart_border_light)
        }
    }

    override fun set_recycler() {
        Handler(context?.mainLooper).post {
            myAdapter = OrderBookAdapter(ArrayList(), context!!, activity as InformationActivity)
            myView.orderbookRecycler.adapter = myAdapter
            myView.orderbookRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
        }
    }

    override fun update_recycler(arr: ArrayList<OrderBook_info>) {
        Handler(context?.mainLooper).post {
            myAdapter?.update(arr)
        }
    }

    override fun start_loading(){
        if(!builder?.isShowing!!){
            Handler(Looper.getMainLooper()).post {
                try { builder?.show() }catch (e: Exception){ }
            }
        }
    }

    fun init_loading(){
        builder = Dialog(context)
        builder?.setContentView(R.layout.waitting_dialog)
        builder?.setCancelable(false)
        builder?.setCanceledOnTouchOutside(false)
        builder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun stop_loading(){
        if (builder?.isShowing!!){
            builder?.dismiss()
        }
    }

}