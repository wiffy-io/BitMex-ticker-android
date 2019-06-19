package com.wiffy.bitmexticker.ui.Information.DetailsFragment

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.get_dialog
import com.wiffy.bitmexticker.extension.get_title
import com.wiffy.bitmexticker.extension.get_title2
import com.wiffy.bitmexticker.extension.setting_button
import com.wiffy.bitmexticker.ui.Information.InformationActivity
import kotlinx.android.synthetic.main.adapter_datails.view.*

class DetailsAdapter(
    var items: ArrayList<Details_info>,
    var context: Context,
    var activity: InformationActivity
) : RecyclerView.Adapter<DetailsAdapter.InformationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = InformationViewHolder(parent)
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
                text_details.setTextColor(ContextCompat.getColor(context, get_title()))
                context_details.setTextColor(ContextCompat.getColor(context, get_title2()))
                text_details.text = item.title

                context_details.text = item.context
                itemView.background = ContextCompat.getDrawable(context, setting_button())
                itemView.setOnClickListener {
                    val builder = AlertDialog.Builder(context, get_dialog())
                    builder.setTitle(item.title)
                    builder.setMessage(item.context)
                    builder.setPositiveButton(
                        "OK"
                    ) { dialog, which ->  }
                    builder.show()
                }
            }
        }
    }

    inner class InformationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_datails, parent, false)
    ) {
        val text_details = itemView.detailsText
        val context_details = itemView.detailsContext
    }
}