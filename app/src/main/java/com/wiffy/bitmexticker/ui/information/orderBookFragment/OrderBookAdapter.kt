package com.wiffy.bitmexticker.ui.information.orderBookFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.getTableOut
import com.wiffy.bitmexticker.extension.getTitle2
import com.wiffy.bitmexticker.ui.information.InformationActivity
import kotlinx.android.synthetic.main.adapter_orderbook.view.*

class OrderBookAdapter(
    var items: ArrayList<OrderBook_info>,
    var context: Context,
    var activity: InformationActivity
) : RecyclerView.Adapter<OrderBookAdapter.OrderBookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OrderBookViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OrderBookViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
                ask.setTextColor(ContextCompat.getColor(context, getTitle2()))
                bid.setTextColor(ContextCompat.getColor(context, getTitle2()))

                if(item.ask == null)
                {
                    price.setTextColor(ContextCompat.getColor(context,R.color.green))
                }
                if(item.bid == null)
                {
                    price.setTextColor(ContextCompat.getColor(context,R.color.red))
                }
                itemView.backgroundTintList = ContextCompat.getColorStateList(context, getTableOut())

                ask.text = item.ask
                price.text = item.price
                bid.text = item.bid
            }
        }
    }
    fun update(list: ArrayList<OrderBook_info>) {
        items = list
        notifyDataSetChanged()
    }

    inner class OrderBookViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_orderbook, parent, false)
    ) {
        val ask = itemView.ask
        val price = itemView.price
        val bid = itemView.bid
    }
}