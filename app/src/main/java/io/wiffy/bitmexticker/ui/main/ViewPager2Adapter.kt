package io.wiffy.bitmexticker.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.getNavi
import kotlinx.android.synthetic.main.adapter_pager.view.*
//
//
// Unused !!
//
//
class ViewPager2Adapter(
    var items: ArrayList<String>,
    var context: Context,
    val mView: MainContract.View
) :
    RecyclerView.Adapter<ViewPager2Adapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ViewPagerViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
               text.text = item
                itemView.background = ContextCompat.getDrawable(context, getNavi())
                itemView.setOnClickListener{
                    Toast.makeText(context,item,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun updateTheme() {
        notifyDataSetChanged()
    }


    inner class ViewPagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_pager, parent, false)
    ) {
        val text:TextView = itemView.textee
    }

}