package com.pale_cosmos.bitmexticker.ui.Main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_table_in
import com.pale_cosmos.bitmexticker.extension.get_table_in_reverse
import com.pale_cosmos.bitmexticker.extension.get_title
import com.pale_cosmos.bitmexticker.extension.get_title2
import com.pale_cosmos.bitmexticker.model.Coin_info
import com.pale_cosmos.bitmexticker.ui.Information.InformationActivity
import kotlinx.android.synthetic.main.main_adapter.view.*
import java.lang.Exception
import java.util.concurrent.ConcurrentHashMap


class MainAdapter(
    var items: ArrayList<Coin_info>,
    var context: Context,
    var is_dark: Boolean,
    val mView: MainContract.View
) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var istouch = false

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MainViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
                bg.setCardBackgroundColor(ContextCompat.getColor(context, get_table_in()))
                symbol.setTextColor(ContextCompat.getColor(context, get_title()))
                name_info.setTextColor(ContextCompat.getColor(context, get_title2()))

                symbol.text = item.Symbol
                name_info.text = item.name_info
                price.text = item.price
                when (item.before_p) {
                    "n" -> card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.normal))
                    "r" -> card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red))
                    "g" -> card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                itemView.setOnClickListener {
                    var bundle = Bundle()
                    bundle.putString("information", item.Symbol)
                    mView.moveToInformation(bundle)
                }
                itemView.setOnTouchListener { v, e ->
                    when (e?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            (v as CardView).setCardBackgroundColor(
                                ContextCompat.getColor(
                                    context,
                                    get_table_in_reverse()
                                )
                            )
                            istouch = true
                        }
                        MotionEvent.ACTION_UP -> {
                            (v as CardView).setCardBackgroundColor(ContextCompat.getColor(context, get_table_in()))
                            v.callOnClick()
                            istouch = false
                            try {
                                notifyDataSetChanged()
                            } catch (e: Exception) {
                            }
                        }
                        MotionEvent.ACTION_CANCEL -> {
                            (v as CardView).setCardBackgroundColor(ContextCompat.getColor(context, get_table_in()))
                            istouch = false
                            try {
                                notifyDataSetChanged()
                            } catch (e: Exception) {
                            }
                        }
                    }
                    true
                }
            }
        }
    }

    fun update_theme(theme: Boolean) {
        is_dark = theme
        notifyDataSetChanged()
    }

    fun update(modelList: ArrayList<Coin_info>) {
        items = modelList
        if (!istouch) {
            notifyDataSetChanged()
        }
        //Log.d("asdasd",modelList[0].get("price"))
        //notifyItemRangeChanged(0, items.size);
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemChanged(position, items.size)
    }

    fun deleteAll() {
        items = ArrayList()
        notifyDataSetChanged()
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.main_adapter, parent, false)
    ) {
        val symbol = itemView.Symbol
        val name_info = itemView.name_info
        val price = itemView.price
        val card_in = itemView.card_in
        val bg = itemView.bg
    }

}