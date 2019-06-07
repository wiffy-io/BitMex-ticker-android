package com.pale_cosmos.bitmexticker.ui.Main


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pale_cosmos.bitmexticker.R
import kotlinx.android.synthetic.main.main_adapter.view.*
import java.util.concurrent.ConcurrentHashMap


class MainAdapter(var items: ArrayList<ConcurrentHashMap<String, String>>, var context:Context, var is_dark: Boolean)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
//
//    var items = ArrayList<ConcurrentHashMap<String, String>>()
//
//    init {
//        this.items = items
//    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MainViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
                if (is_dark){
                    bg.setCardBackgroundColor(ContextCompat.getColor(context,R.color.dark_table_out))
                }else{
                    bg.setCardBackgroundColor(ContextCompat.getColor(context,R.color.light_table_out))
                }
                symbol.text = item.get("Symbol")
                name_info.text = item.get("name_info")
                price.text = item.get("price")
                if (item.get("before_p") == "n"){
                    card_in.setCardBackgroundColor(ContextCompat.getColor(context,R.color.normal))
                }else if (item.get("before_p") == "r"){
                    card_in.setCardBackgroundColor(ContextCompat.getColor(context,R.color.red))
                }else if (item.get("before_p") == "g"){
                    card_in.setCardBackgroundColor(ContextCompat.getColor(context,R.color.green))
                }
            }
        }
    }

    fun update_theme(theme:Boolean){
        is_dark = theme
        notifyDataSetChanged()
    }

    fun update(modelList:ArrayList<ConcurrentHashMap<String, String>>){
        items = modelList
        notifyDataSetChanged()
        //Log.d("asdasd",modelList[0].get("price"))
        //notifyItemRangeChanged(0, items.size);
    }

    fun deleteItem(position:Int)
    {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemChanged(position,items.size)
    }
    fun deleteAll()
    {
        items = ArrayList()
        notifyDataSetChanged()
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.main_adapter, parent, false)) {
        val symbol = itemView.Symbol
        val name_info = itemView.name_info
        val price = itemView.price
        val card_in = itemView.card_in
        val bg = itemView.bg
    }
}