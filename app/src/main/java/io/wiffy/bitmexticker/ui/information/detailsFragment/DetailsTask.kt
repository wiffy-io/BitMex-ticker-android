package io.wiffy.bitmexticker.ui.information.detailsFragment

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.net.URL

class DetailsTask(act:DetailsContract.View, url:String) : AsyncTask<String, Void, Boolean>() {

    val mView = act
    private val mUrl = url
    lateinit var arr: ArrayList<DetailsInfo>

    override fun onPreExecute() {
        arr = ArrayList()
        super.onPreExecute()
        mView.asyncPre()
    }

    override fun doInBackground(vararg params: String?): Boolean {
        var rows: Elements? = null
        for (x in Jsoup.parseBodyFragment(URL(mUrl).readText()).select("table")) {
            if (x.select("tr")[0].select("td")[0].text() == "Ticker Root") {
                rows = x.select("tr")
                break
            }
        }
        if (rows != null) {
            for (n in 0 until rows.size) {
                arr.add(
                    DetailsInfo(
                        rows[n].select("td")[0].text(),
                        rows[n].select("td")[1].text()
                    )
                )
            }
            Handler(Looper.getMainLooper()).post {
                mView.updateRecycler(arr)
            }
            return true
        }
        return false
    }

    override fun onPostExecute(result: Boolean?) {
        mView.asyncPost()
        return
    }
}