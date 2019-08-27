package io.wiffy.bitmexticker.ui.information.detailsFragment.tool

import android.os.Handler
import android.os.Looper
import io.wiffy.bitmexticker.model.SuperContract
import io.wiffy.bitmexticker.ui.information.detailsFragment.DetailsContract
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.net.URL

class DetailsTask(val mView: DetailsContract.View, private val mUrl: String) :
    SuperContract.SuperAsyncTask<String, Void, Boolean>() {

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
        rows?.let {
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
    }
}
