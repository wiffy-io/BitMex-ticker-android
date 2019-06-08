package com.pale_cosmos.bitmexticker.ui.Main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_table_in
import com.pale_cosmos.bitmexticker.extension.get_title
import com.pale_cosmos.bitmexticker.extension.get_title2
import com.pale_cosmos.bitmexticker.ui.Information.InformationActivity
import kotlinx.android.synthetic.main.main_adapter.view.*
import java.util.concurrent.ConcurrentHashMap


class MainAdapter(var items: ArrayList<ConcurrentHashMap<String, String>>, var context: Context, var is_dark: Boolean, val mView:MainContract.View) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MainViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
                bg.setCardBackgroundColor(ContextCompat.getColor(context, get_table_in()))
                symbol.setTextColor(ContextCompat.getColor(context, get_title()))
                name_info.setTextColor(ContextCompat.getColor(context, get_title2()))

//                symbol.text = item.get("Symbol")
//                name_info.text = item.get("name_info")
//                price.text = item.get("price")
//                if (item.get("before_p") == "n") {
//                    card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.normal))
//                } else if (item.get("before_p") == "r") {
//                    card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red))
//                } else if (item.get("before_p") == "g") {
//                    card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
//                }
                // 코드 간결화 작업

                symbol.text = item["Symbol"]
                name_info.text = item["name_info"]
                price.text = item["price"]
                when (item["before_p"]) {
                    "n" -> card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.normal))
                    "r" -> card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red))
                    "g" -> card_in.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                itemView.setOnClickListener {
                    var bundle = Bundle()
//                    bundle.putXXX(XX,XX)
                    mView.moveToInformation(bundle)
                }
                itemView.setOnTouchListener { v, event ->
                    when(event.action)
                    {
                        MotionEvent.ACTION_DOWN->{
                            v.bg.setCardBackgroundColor(get_title())
                        }
                        MotionEvent.ACTION_UP->{
                            v.bg.setCardBackgroundColor(get_table_in())
                        }
                        MotionEvent.ACTION_CANCEL->{
                            v.bg.setCardBackgroundColor(get_table_in())
                        }
                        else->{}
                    }
                    false
                }
            }
        }
    }

    fun update_theme(theme: Boolean) {
        is_dark = theme
        notifyDataSetChanged()
    }

    fun update(modelList: ArrayList<ConcurrentHashMap<String, String>>) {
        items = modelList
        notifyDataSetChanged()
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