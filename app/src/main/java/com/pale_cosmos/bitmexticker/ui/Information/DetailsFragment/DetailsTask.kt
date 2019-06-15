package com.pale_cosmos.bitmexticker.ui.Information.DetailsFragment

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.net.URL

class DetailsTask(act:DetailsConstract.View,url:String) : AsyncTask<String, Void, Boolean>(), DetailsConstract.Task {

    val mView = act
    val url = url
    lateinit var arr: ArrayList<Details_info>

    override fun onPreExecute() {
        arr = ArrayList()
        super.onPreExecute()
        mView.async_pre()
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
                arr.add(
                    Details_info(
                        rows[n].select("td")[0].text(),
                        rows[n].select("td")[1].text()
                    )
                )
            }
            Handler(Looper.getMainLooper()).post {
                mView.update_recycler(arr)
            }
            return true
        }
        return false
    }

    override fun onPostExecute(result: Boolean?) {
        mView.async_post()
        return
    }
}
