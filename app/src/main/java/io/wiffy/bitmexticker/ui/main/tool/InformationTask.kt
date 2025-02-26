package io.wiffy.bitmexticker.ui.main.tool

import android.annotation.SuppressLint
import io.wiffy.bitmexticker.function.getShared
import io.wiffy.bitmexticker.function.getTimeFormat
import io.wiffy.bitmexticker.function.setShared
import io.wiffy.bitmexticker.model.Component
import io.wiffy.bitmexticker.model.SuperContract
import io.wiffy.bitmexticker.ui.main.MainContract
import io.wiffy.bitmexticker.ui.main.MainPresenter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.collections.ArrayList

@SuppressLint("StaticFieldLeak")
class InformationTask(private val mPresenter: MainPresenter, private val mView: MainContract.View) :
    SuperContract.SuperAsyncTask<Void, Void, ArrayList<String>>() {

    override fun doInBackground(vararg params: Void?): ArrayList<String> {
        val url = "http://wiffy.io/bitmex/hello/and.html?${getTimeFormat("yyyyMMddHHmmss")}"
        try {

            BufferedReader(InputStreamReader((URL(url).openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"
                )
                setRequestProperty("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
                setRequestProperty("Accept-Encoding", "gzip, deflate")
                setRequestProperty("Accept-Language", "ko-KR")
                setRequestProperty("Connection", "Keep-Alive")
                setRequestProperty("Host", "wiffy.io")
            }.inputStream)).run {
                var inputLine: String?
                val response = StringBuffer()
                do {
                    inputLine = readLine()
                    if (inputLine == null) break
                    else response.append(inputLine)
                } while (true)
                close()
            }
        } catch (e: Exception) {
        }
        return try {
            mPresenter.parseInformation()
        } catch (e: Exception) {
            temp()
        }
    }

    override fun onPostExecute(result: ArrayList<String>?) {
        mView.setInformation(result)
    }

    private fun temp() = ArrayList<String>().apply {
        add("Error")
    }
}