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


class MainAdapter(var items: ArrayList<ConcurrentHashMap<String, String>>, var context:Context)
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
                symbol.text = items[position].get("Symbol")
                name_info.text = items[position].get("name_info")
                price.text = items[position].get("price")
                if (items[position].get("before_p") == "n"){
                    //card_in.setCardBackgroundColor(color);
                    card_in.setBackgroundColor(ContextCompat.getColor(context,R.color.normal))
                }else if (items[position].get("before_p") == "r"){
                    card_in.setBackgroundColor(ContextCompat.getColor(context,R.color.red))
                }else if (items[position].get("before_p") == "g"){
                    card_in.setBackgroundColor(ContextCompat.getColor(context,R.color.green))
                }
            }
        }
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
    }
}