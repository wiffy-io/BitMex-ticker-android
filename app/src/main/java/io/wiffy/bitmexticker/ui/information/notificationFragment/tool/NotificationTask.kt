package io.wiffy.bitmexticker.ui.information.notificationFragment.tool

import io.wiffy.bitmexticker.function.ifNotNullOrElse
import io.wiffy.bitmexticker.model.SuperContract
import io.wiffy.bitmexticker.ui.information.notificationFragment.NotificationFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class NotificationTask(val mView: NotificationFragment, private val info: NotificationInfo) : SuperContract.SuperAsyncTask<Void, Void, Int>() {

    override fun doInBackground(vararg params: Void?): Int {
        val url = "http://wiffy.io/bitmex/reg/?d=alert_${info.symbol}:${info.value}"
        try {
            val request = (URL(url).openConnection() as HttpURLConnection).apply {
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
            }

            val `in` = BufferedReader(InputStreamReader(request.inputStream))
            var inputLine: String?
            val response = StringBuffer()

            do {
                inputLine = `in`.readLine()
                if (inputLine == null) break
                else response.append(inputLine)
            } while (true)
            `in`.close()
            if (!(response.toString().trim().contains("OK"))) return -1
        } catch (e: Exception) {
            return -1
        }

        return 0
    }

    override fun onPostExecute(result: Int?) {
        if (result == -1) {
            mView.toast("Error")
        } else {
            mView.setList(info)
        }
    }
}