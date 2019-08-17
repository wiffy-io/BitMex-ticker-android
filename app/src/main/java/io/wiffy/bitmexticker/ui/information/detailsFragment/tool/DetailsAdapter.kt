package io.wiffy.bitmexticker.ui.information.detailsFragment.tool

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.getDialog
import io.wiffy.bitmexticker.extension.getTitle
import io.wiffy.bitmexticker.extension.getTitle2
import io.wiffy.bitmexticker.extension.settingButton
import kotlinx.android.synthetic.main.adapter_datails.view.*

class DetailsAdapter(
    var items: ArrayList<DetailsInfo>,
    var context: Context
) : RecyclerView.Adapter<DetailsAdapter.InformationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = InformationViewHolder(parent)
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) = items[position].let { item ->
        with(holder) {
            textDetails.setTextColor(ContextCompat.getColor(context, getTitle()))
            contextDetails.setTextColor(ContextCompat.getColor(context, getTitle2()))
            textDetails.text = item.title

            contextDetails.text = item.context
            itemView.background = ContextCompat.getDrawable(context, settingButton())
            itemView.setOnClickListener {
                AlertDialog.Builder(context, getDialog()).apply {
                    setTitle(item.title)
                    setMessage(item.context)
                    setPositiveButton(
                        "OK"
                    ) { _, _ -> }
                }.show()
            }
        }
    }

    inner class InformationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_datails, parent, false)
    ) {
        val textDetails: TextView = itemView.detailsText
        val contextDetails: TextView = itemView.detailsContext
    }
}