package io.wiffy.bitmexticker.ui.information.orderBookFragment.tool

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.getTableOut
import io.wiffy.bitmexticker.extension.getTitle2
import io.wiffy.bitmexticker.model.Util
import io.wiffy.bitmexticker.ui.information.InformationActivity
import kotlinx.android.synthetic.main.adapter_orderbook.view.*

class OrderBookAdapter(
    var items: ArrayList<OrderBookInfo>,
    var context: Context,
    var activity: InformationActivity
) : RecyclerView.Adapter<OrderBookAdapter.OrderBookViewHolder>() {
    var count = 0
    var sum = 0
    private var isTouch = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OrderBookViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OrderBookViewHolder, position: Int) = items[position].let { item ->
        with(holder) {
            //            setIsRecyclable(false)
            val flag = item.bid == null
            count += 1
            var ratio = 0.0
            ask.setTextColor(ContextCompat.getColor(context, getTitle2()))
            bid.setTextColor(ContextCompat.getColor(context, getTitle2()))

            if (flag) {
                price.setTextColor(ContextCompat.getColor(context, R.color.red))
                ratio = item.ask?.toDouble()?.div(sum) ?: 0.0
                right.visibility = View.GONE
                left.visibility = View.VISIBLE
            } else {
                price.setTextColor(ContextCompat.getColor(context, R.color.green))
                ratio = item.bid?.toDouble()?.div(sum) ?: 0.0
                left.visibility = View.GONE
                right.visibility = View.VISIBLE
            }
            itemView.backgroundTintList = ContextCompat.getColorStateList(context, getTableOut())

            ask.text = item.ask
            price.text = item.price
            bid.text = item.bid
            if (ratio >= 0.0) {
                if (flag) {
                    left.layoutParams.width =
                        Util.width.times(ratio).toInt()
                } else {
                    right.layoutParams.width =
                        Util.width.times(ratio).toInt()
                }
            }
            itemView.setOnTouchListener { _, e ->
                isTouch = when (e?.action) {
                    MotionEvent.ACTION_DOWN -> true
                    MotionEvent.ACTION_UP -> false
                    else -> false
                }
                true
            }
        }
        if (count >= 20) count = 0

    }

    fun update(list: ArrayList<OrderBookInfo>, sum: Int) {
        Log.d("asdf", "count=$count")
        if (!isTouch && count == 0) {
            this.sum = sum
            items = list
            notifyDataSetChanged()
        } else {

        }
    }

    inner class OrderBookViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_orderbook, parent, false)
    ) {

        val ask: TextView = itemView.ask
        val price: TextView = itemView.price
        val bid: TextView = itemView.bid
        val right: View = itemView.percentageViewRight
        val left: View = itemView.percentageViewLeft
    }
}