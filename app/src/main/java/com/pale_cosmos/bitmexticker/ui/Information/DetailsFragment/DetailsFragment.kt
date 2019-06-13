package com.pale_cosmos.bitmexticker.ui.Information.DetailsFragment

import android.app.Dialog
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_fragment_background
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.ui.Information.InformationAdapter
import kotlinx.android.synthetic.main.fragment_details.*
import org.jsoup.Jsoup
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ConcurrentHashMap
import javax.net.ssl.HttpsURLConnection

class DetailsFragment : Fragment(), DetailsConstract.View {
    lateinit var myView: View
    lateinit var mPresenter: DetailsPresenter
    lateinit var parentLayout: RelativeLayout
    lateinit var recycler: RecyclerView
    lateinit var url:String
    var myAdapter: InformationAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_details, container, false)
        recycler = myView.findViewById(R.id.detailsRecycler)
        url = "https://www.bitmex.com/app/contract/${arguments?.getString("symbol")}"
        Log.d("table",url)
        mPresenter = DetailsPresenter(this)
        mPresenter.init()
        return myView
    }

    override fun changeUI() {

        parentLayout = myView.findViewById(R.id.details)
        parentLayout.background = resources.getDrawable(get_fragment_background())
        task().execute()

    }


    inner class task:AsyncTask<String,Void,Boolean>()
    {
        var builder = Dialog(context!!)
        override fun onPreExecute() {
            super.onPreExecute()

//            builder.setContentView(R.layout.activity_dialog)
//            builder.setCancelable(false)
//            builder.setCanceledOnTouchOutside(false)
//            builder.setOnShowListener {
//                var x =builder.findViewById<Button>(R.id.OKBUTTON)
//                x.setOnClickListener {
////                    builder.dismiss()
//                }
//                var y = builder.findViewById<TextView>(R.id.contextInDialog)
//                y.text = "버튼누르지마"
//                var z = builder.findViewById<TextView>(R.id.titleInDialog)
//                z.text= "좀만기달려봐"
//            }
//            builder.window?.setBackgroundDrawableResource(android.R.color.transparent)
//            Handler().post {
//                builder.show()
//            }
        }


        override fun doInBackground(vararg params: String?): Boolean {
//            var doc = Jsoup.connect(url).get()
////            var doc = Jsoup.connect("https://www.naver.com").get()
//            var mElementDataSize = doc.select(
//                "table")
//            for(table in mElementDataSize)
//            {
//                Log.d("table",table.className())
//            }
            var rl = URL(url)
//            with(rl.openConnection() as HttpURLConnection){
//                requestMethod = "GET"
//                inputStream.bufferedReader().use{
//                    it.lines().forEach{line->
//                        Log.d("table",line)
//                    }
//                }
//            }
            Log.d("table",rl.readText())
            return true
        }

        override fun onPostExecute(result: Boolean?) {

//            builder.dismiss()
            return
        }
    }
}