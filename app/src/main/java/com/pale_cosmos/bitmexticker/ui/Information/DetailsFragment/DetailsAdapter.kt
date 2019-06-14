package com.pale_cosmos.bitmexticker.ui.Information.DetailsFragment

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.details_state_color
import com.pale_cosmos.bitmexticker.extension.get_title
import com.pale_cosmos.bitmexticker.extension.get_title2
import com.pale_cosmos.bitmexticker.ui.Information.InformationActivity
import com.pale_cosmos.bitmexticker.ui.Information.details_info
import kotlinx.android.synthetic.main.adapter_datails.view.*

class DetailsAdapter(
    var items: ArrayList<details_info>,
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
                itemView.backgroundTintList = ContextCompat.getColorStateList(context, details_state_color())
                itemView.setOnClickListener {
                    var builder = Dialog(activity)
                    builder.setContentView(R.layout.activity_dialog)
                    builder.setCancelable(false)
                    builder.setCanceledOnTouchOutside(false)
                    builder.setOnShowListener {
                        var x = builder.findViewById<Button>(R.id.OKBUTTON)
                        x.setOnClickListener {
                            builder.dismiss()
                        }
                        var y = builder.findViewById<TextView>(R.id.contextInDialog)
                        y.text = item.context
                        var z = builder.findViewById<TextView>(R.id.titleInDialog)
                        z.text = item.title
                    }
                    builder.window?.setBackgroundDrawableResource(android.R.color.transparent)
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