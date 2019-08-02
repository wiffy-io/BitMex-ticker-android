package io.wiffy.bitmexticker.ui.main


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.*
import io.wiffy.bitmexticker.model.CoinInfo
import io.wiffy.bitmexticker.model.Util.Companion.info_on
import kotlinx.android.synthetic.main.adapter_main.view.*
import java.lang.Exception

class MainAdapter(
    var items: ArrayList<CoinInfo>,
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
                bg.setCardBackgroundColor(ContextCompat.getColor(context, getTableIn()))
                //bg.setBackgroundResource(settingButton())
                symbol.setTextColor(ContextCompat.getColor(context, getTitle()))
                nameInfo.setTextColor(ContextCompat.getColor(context, getTitle2()))

                symbol.text = item.Symbol
                nameInfo.text = item.name_info
                price.text = item.price

                when (item.before_p) {
                    "n" -> cardIn.setCardBackgroundColor(ContextCompat.getColor(context, R.color.normal))
                    "r" -> cardIn.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red))
                    "g" -> cardIn.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }

                itemView.setOnTouchListener { _, e ->
                    when (e?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            actionDown(bg)
                        }
                        MotionEvent.ACTION_UP -> {
                            actionUp(bg, item.price!!, item.Symbol!!, item)
                        }
                        MotionEvent.ACTION_CANCEL -> {
                            actionCancel(bg)
                        }
                    }
                    true
                }
            }
        }
    }

    private fun actionDown(bg: CardView) {
        bg.setCardBackgroundColor(ContextCompat.getColor(context, getTableInReverse()))
        istouch = true
    }

    private fun actionUp(bg: CardView, price: String, sym: String, data: CoinInfo) {
        bg.setCardBackgroundColor(ContextCompat.getColor(context, getTableIn()))
        if (!price.contains("-") && info_on) {
            click(sym, data)
        }
        istouch = false
        notificationUpdate()
    }

    private fun actionCancel(bg: CardView) {
        bg.setCardBackgroundColor(ContextCompat.getColor(context, getTableIn()))
        istouch = false
        notificationUpdate()
    }

    private fun click(str: String, item: CoinInfo) {
        info_on = false
        val bundle = Bundle().apply {
            putString("information", str)
            putString("xbt", items[0].price)
            putSerializable("data", item)
        }
        mView.moveToInformation(bundle)
    }

    fun updateTheme(theme: Boolean) {
        is_dark = theme
        notificationUpdate()
    }

    fun update(modelList: ArrayList<CoinInfo>) {
        items = modelList
        if (!istouch) {
            notificationUpdate()
        }
    }

    private fun notificationUpdate() {
        try {
            notifyDataSetChanged()
        } catch (e: Exception) {
        }
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    ) {
        val symbol: TextView = itemView.Symbol
        val nameInfo: TextView = itemView.name_info
        val price: TextView = itemView.price
        val cardIn: CardView = itemView.card_in
        val bg: CardView = itemView.bg
    }

}