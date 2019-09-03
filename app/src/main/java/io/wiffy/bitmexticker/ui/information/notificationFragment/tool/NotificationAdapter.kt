package io.wiffy.bitmexticker.ui.information.notificationFragment.tool

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.messaging.FirebaseMessaging
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.*
import io.wiffy.bitmexticker.model.Component
import io.wiffy.bitmexticker.model.SuperContract
import io.wiffy.bitmexticker.ui.information.notificationFragment.NotificationContract
import kotlinx.android.synthetic.main.adapter_notification.view.*

class NotificationAdapter(
    var items: ArrayList<NotificationInfo>,
    val mView: NotificationContract.View,
    private val symbolOut: String?
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>(), SuperContract.WiffyObject {

    val context = mView.sendContext()!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotificationViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) =
        items[position].let { item ->
            with(holder) {
                mySymbol.text = item.symbol
                myValue.text = item.value
                mySymbol.setTextColor(ContextCompat.getColor(context, getTitle()))
                myValue.setTextColor(ContextCompat.getColor(context, getTitle2()))
                myRealButton.setOnClickListener {
                    mView.builderUp()
                    FirebaseMessaging.getInstance()
                        .unsubscribeFromTopic("${item.symbol}_${item.value}")
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                items.remove(item)
                                notifyDataSetChanged()
                                val set = HashSet<String>()
                                for (x in items) {
                                    set.add("${x.symbol}:${x.value}:${x.date}")
                                }
                                Component.notificationSet = set
                                setShared("notificationSet", set)
                            } else {
                                toast(context, "Error")
                            }
                            Handler().postDelayed(
                                { mView.builderDismiss() }, 500
                            )
                        }
                }
                cardIn.setCardBackgroundColor(ContextCompat.getColor(context, getTableIn()))
                if (symbolOut != item.symbol) {
                    itemView.visibility = View.GONE
                    itemView.layoutParams =
                        (itemView.layoutParams as RecyclerView.LayoutParams).apply {
                            height = 0
                            width = 0
                        }
                } else {
                    itemView.visibility = View.VISIBLE
                    itemView.layoutParams =
                        (itemView.layoutParams as RecyclerView.LayoutParams).apply {
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
        val cardIn: CardView = itemView.cardback
        val myValue: TextView = itemView.textextext
        val mySymbol: TextView = itemView.notitextex
        val myRealButton: RelativeLayout = itemView.deletion22
    }
}