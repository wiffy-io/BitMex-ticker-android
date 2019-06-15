package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_table_out
import com.pale_cosmos.bitmexticker.extension.get_title2
import com.pale_cosmos.bitmexticker.ui.Information.InformationActivity
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
                ask.setTextColor(ContextCompat.getColor(context, get_title2()))
                bid.setTextColor(ContextCompat.getColor(context, get_title2()))
//                price.setTextColor()
                itemView.backgroundTintList = ContextCompat.getColorStateList(context, get_table_out())

                ask.text = item.ask
                price.text = item.price
                bid.text = item.bid
            }
        }
    }

    inner class OrderBookViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_orderbook, parent, false)
    ) {
        val ask = itemView.ask
        val price = itemView.price
        val bid = itemView.bid
    }
}