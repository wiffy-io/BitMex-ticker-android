package com.wiffy.bitmexticker.ui.information.detailsFragment

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.getDialog
import com.wiffy.bitmexticker.extension.getTitle
import com.wiffy.bitmexticker.extension.getTitle2
import com.wiffy.bitmexticker.extension.settingButton
import com.wiffy.bitmexticker.ui.information.InformationActivity
import kotlinx.android.synthetic.main.adapter_datails.view.*

class DetailsAdapter(
    var items: ArrayList<DetailsInfo>,
    var context: Context
) : RecyclerView.Adapter<DetailsAdapter.InformationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = InformationViewHolder(parent)
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
                textDetails.setTextColor(ContextCompat.getColor(context, getTitle()))
                contextDetails.setTextColor(ContextCompat.getColor(context, getTitle2()))
                textDetails.text = item.title

                contextDetails.text = item.context
                itemView.background = ContextCompat.getDrawable(context, settingButton())
                itemView.setOnClickListener {
                    val builder = AlertDialog.Builder(context, getDialog())
                    builder.setTitle(item.title)
                    builder.setMessage(item.context)
                    builder.setPositiveButton(
                        "OK"
                    ) { _, _ ->  }
                    builder.show()
                }
            }
        }
    }

    inner class InformationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_datails, parent, false)
    ) {
        val textDetails:TextView = itemView.detailsText
        val contextDetails:TextView = itemView.detailsContext
    }
}