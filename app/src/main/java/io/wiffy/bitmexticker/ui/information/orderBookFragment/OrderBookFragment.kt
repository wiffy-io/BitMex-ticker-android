package io.wiffy.bitmexticker.ui.information.orderBookFragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.getFragmentBackground
import io.wiffy.bitmexticker.model.Util.Companion.dark_theme
import io.wiffy.bitmexticker.ui.information.InformationActivity
import io.wiffy.bitmexticker.ui.information.orderBookFragment.tool.OrderBookAdapter
import io.wiffy.bitmexticker.ui.information.orderBookFragment.tool.OrderBookInfo
import kotlinx.android.synthetic.main.fragment_orderbook.view.*
import java.lang.Exception

class OrderBookFragment : OrderBookContract.View() {
    lateinit var myView: View
    lateinit var mPresenter: OrderBookPresenter
    var builder: Dialog? = null
    var myAdapter: OrderBookAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_orderbook, container, false)
        initLoading()
        val sym = arguments?.getString("symbol")!!
        mPresenter = OrderBookPresenter(this, sym)
        mPresenter.init()
        return myView
    }

    override fun onResume() {
        super.onResume()
        mPresenter.startWs()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.stopWs()
    }

    override fun changeUI() = with(resources)
    {
        myView.orderbooks.background = getDrawable(getFragmentBackground())
        if (dark_theme) {
            myView.orderbook_view.background = getDrawable(R.drawable.chart_border_dark)
            myView.orderbook_large.background = getDrawable(R.drawable.chart_border_dark)
        } else {
            myView.orderbook_view.background = getDrawable(R.drawable.chart_border_light)
            myView.orderbook_large.background = getDrawable(R.drawable.chart_border_light)
        }
    }


    override fun setRecycler() = Handler(context?.mainLooper).post {
        myAdapter = OrderBookAdapter(
            ArrayList(),
            context!!,
            activity as InformationActivity
        )

        myView.orderbookRecycler.setHasFixedSize(true)
        myView.orderbookRecycler.adapter = myAdapter
        myView.orderbookRecycler.setItemViewCacheSize(20)
        myView.orderbookRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
    }


    override fun updateRecycler(arr: ArrayList<OrderBookInfo>, sum: Int) = Handler(context?.mainLooper).post {
        myAdapter?.update(arr, sum)
    }

    override fun startLoading() {
        if (builder?.isShowing == false) Handler(Looper.getMainLooper()).post {
            try {
                builder?.show()
            } catch (e: Exception) {
            }
        }
    }

    private fun initLoading() {
        builder = Dialog(context!!).apply {
            setContentView(R.layout.waitting_dialog)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun stopLoading() {
        if (builder?.isShowing == true) builder?.dismiss()
    }

    override fun changeRecent(str: String) {
        myView.orderbook_view.text = str
    }
}