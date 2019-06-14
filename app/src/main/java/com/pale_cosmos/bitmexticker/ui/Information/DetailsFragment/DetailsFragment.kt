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
import com.pale_cosmos.bitmexticker.ui.Information.details_info
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.net.URL

class DetailsFragment : Fragment(), DetailsConstract.View {
    lateinit var myView: View
    lateinit var mPresenter: DetailsPresenter
    lateinit var parentLayout: RelativeLayout
    lateinit var recycler: RecyclerView
    lateinit var url: String
    lateinit var arr: ArrayList<details_info>
    var builder: Dialog? = null
    var myAdapter: DetailsAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_details, container, false)
        recycler = myView.findViewById(R.id.detailsRecycler)
        url = "https://www.bitmex.com/app/contract/${arguments?.getString("symbol")}"
        Log.d("table", url)
        mPresenter = DetailsPresenter(this)
        mPresenter.init()
        return myView
    }

    override fun changeUI() {

        parentLayout = myView.findViewById(R.id.details)
        parentLayout.background = resources.getDrawable(get_fragment_background())
        arr = ArrayList()
        task().execute()

    }

    override fun onDestroy() {
        if (builder != null) builder?.dismiss()
        super.onDestroy()
    }


    inner class task : AsyncTask<String, Void, Boolean>() {


        override fun onPreExecute() {
            super.onPreExecute()

            builder = Dialog(activity!!)
            builder?.setContentView(R.layout.waitting_dialog)
            builder?.setCancelable(false)
            builder?.setCanceledOnTouchOutside(false)
            builder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            Handler(Looper.getMainLooper()).post {
                builder?.show()
            }


        }


        override fun doInBackground(vararg params: String?): Boolean {
            var rows: Elements? = null
            for (x in Jsoup.parseBodyFragment(URL(url).readText()).select("table")) {
                if (x.select("tr")[0].select("td")[0].text() == "Ticker Root") {
                    rows = x.select("tr")
                    break
                }
            }
            if (rows != null) {
                for (n in 0 until rows.size) {
                    arr.add(details_info(rows[n].select("td")[0].text(), rows[n].select("td")[1].text()))
                }
                Handler(Looper.getMainLooper()).post {
                    myAdapter = DetailsAdapter(
                        arr,
                        context!!,
                        activity as InformationActivity
                    )
                    recycler.adapter = myAdapter
                    recycler.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
                }
                return true
            }
            return false
        }

        override fun onPostExecute(result: Boolean?) {

            builder?.dismiss()
            return
        }
    }

}