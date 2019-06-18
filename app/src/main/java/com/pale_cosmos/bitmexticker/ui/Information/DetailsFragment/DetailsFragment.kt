package com.pale_cosmos.bitmexticker.ui.Information.DetailsFragment

import android.app.Dialog
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_fragment_background
import com.pale_cosmos.bitmexticker.ui.Information.InformationActivity
import kotlinx.android.synthetic.main.fragment_details.view.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.net.URL

class DetailsFragment : Fragment(), DetailsConstract.View {
    lateinit var myView: View
    lateinit var mPresenter: DetailsPresenter
    lateinit var url: String
    lateinit var async_task:DetailsTask

    var builder: Dialog? = null
    var myAdapter: DetailsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_details, container, false)
        url = "https://www.bitmex.com/app/contract/${arguments?.getString("symbol")}"
        async_task = DetailsTask(this,url)

        mPresenter = DetailsPresenter(this)
        mPresenter.init()

        return myView
    }

    override fun changeUI() {
        myView.details.background = resources.getDrawable(get_fragment_background())
        async_task.execute()
    }

    override fun onDestroy() {
        if (builder != null) builder?.dismiss()
        super.onDestroy()
    }

    override fun async_pre(){
        builder = Dialog(activity!!)
        builder?.setContentView(R.layout.waitting_dialog)
        builder?.setCancelable(false)
        builder?.setCanceledOnTouchOutside(false)
        builder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        Handler(Looper.getMainLooper()).post {
            builder?.show()
        }
    }

    override fun update_recycler(arr:ArrayList<Details_info>){
        myAdapter = DetailsAdapter(
            arr,
            context!!,
            activity as InformationActivity
        )
        myView.detailsRecycler.adapter = myAdapter
        myView.detailsRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
    }

    override fun async_post(){
        builder?.dismiss()
    }
    
}