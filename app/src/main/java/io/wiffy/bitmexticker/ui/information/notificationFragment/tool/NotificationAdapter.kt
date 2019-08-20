package io.wiffy.bitmexticker.ui.information.notificationFragment.tool

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.messaging.FirebaseMessaging
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.dpToPx
import io.wiffy.bitmexticker.function.getTableIn
import io.wiffy.bitmexticker.function.setShared
import io.wiffy.bitmexticker.model.Util
import io.wiffy.bitmexticker.model.Util.dark_theme
import io.wiffy.bitmexticker.ui.information.InformationActivity
import kotlinx.android.synthetic.main.adapter_notification.view.*

class NotificationAdapter(
    var items: ArrayList<NotificationInfo>,
    var context: Context,
    var activity: InformationActivity,
    private val symbolOut: String?
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NotificationViewHolder(parent)
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) = items[position].let { item ->
        with(holder) {
            mySymbol.text = item.symbol
            myValue.text = item.value
            val color = if (dark_theme) {
                R.color.WHITE
            } else {
                R.color.BLACK
            }
            mySymbol.setTextColor(ContextCompat.getColor(context, color))
            myValue.setTextColor(ContextCompat.getColor(context, color))
            myButton.setOnClickListener {
                items.remove(item)
                notifyDataSetChanged()
                val set = HashSet<String>()
                for (x in items) {
                    set.add("${x.symbol}:${x.value}:${x.date}")
                }
                Util.notificationSet = set
                setShared("notificationSet", set)
                FirebaseMessaging.getInstance().unsubscribeFromTopic("${item.symbol}_${item.value}")
            }
            itemView.setBackgroundColor(ContextCompat.getColor(context, getTableIn()))
            if (symbolOut != item.symbol) {
                itemView.visibility = View.GONE
                itemView.layoutParams = (itemView.layoutParams as RecyclerView.LayoutParams).apply {
                    height = 0
                    width = 0
                }
            } else {
                itemView.visibility = View.VISIBLE
                itemView.layoutParams = (itemView.layoutParams as RecyclerView.LayoutParams).apply {
                    height = dpToPx(context, 60)
                    width = LinearLayout.LayoutParams.MATCH_PARENT
                }
            }
        }
    }

    fun update(list: ArrayList<NotificationInfo>) {
        items = list
        notifyDataSetChanged()
    }


    inner class NotificationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_notification, parent, false)
    ) {
        val myValue: TextView = itemView.textextext
        val mySymbol: TextView = itemView.notitextex
        val myButton: TextView = itemView.deletion
    }
}