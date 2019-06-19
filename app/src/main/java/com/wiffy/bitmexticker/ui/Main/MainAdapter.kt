package com.wiffy.bitmexticker.ui.Main


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.*
import com.wiffy.bitmexticker.model.Coin_info
import com.wiffy.bitmexticker.model.Util.Companion.info_on
import kotlinx.android.synthetic.main.adapter_main.view.*
import java.lang.Exception

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
                //bg.setBackgroundResource(setting_button())
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

                itemView.setOnTouchListener { v, e ->
                    when (e?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            action_down(bg)
                        }
                        MotionEvent.ACTION_UP -> {
                            action_up(bg,item.price!!,item.Symbol!!)
                        }
                        MotionEvent.ACTION_CANCEL -> {
                            action_cancel(bg)
                        }
                    }
                    true
                }
            }
        }
    }

    private fun action_down(bg:CardView){
        bg.setCardBackgroundColor(ContextCompat.getColor(context, get_table_in_reverse()))
        istouch = true
    }

    private fun action_up(bg:CardView,price:String,sym:String){
        bg.setCardBackgroundColor(ContextCompat.getColor(context, get_table_in()))
        if (!price.contains("-")!! && info_on) {
            click(sym)
        }
        istouch = false
        noti_update()
    }

    private fun action_cancel(bg:CardView){
        bg.setCardBackgroundColor(ContextCompat.getColor(context, get_table_in()))
        istouch = false
        noti_update()
    }

    private fun click(str:String){
        info_on = false
        var bundle = Bundle()
        bundle.putString("information", str)
        mView.moveToInformation(bundle)
    }

    fun update_theme(theme: Boolean) {
        is_dark = theme
        noti_update()
    }

    fun update(modelList: ArrayList<Coin_info>) {
        items = modelList
        if (!istouch) {
            noti_update()
        }
    }

    fun noti_update(){
        try {
            notifyDataSetChanged()
        } catch (e: Exception) {
        }
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    ) {
        val symbol = itemView.Symbol
        val name_info = itemView.name_info
        val price = itemView.price
        val card_in = itemView.card_in
        val bg = itemView.bg
    }

}