package io.wiffy.bitmexticker.ui.information.mainFragment

import android.os.Handler
import android.os.Looper
import android.util.Log
import io.wiffy.bitmexticker.extension.changeValue
import io.wiffy.bitmexticker.model.CoinInfo
import io.wiffy.bitmexticker.model.Util.Companion.dark_theme
import org.json.JSONObject
import org.jsoup.Jsoup
import java.lang.Exception
import java.lang.Thread.sleep
import java.net.URL


class MainPresenter(act: MainContract.View, data: CoinInfo?) : MainContract.Presenter {


    private val mView = act
    private val mData = data
    private val mSymbol = data?.Symbol
    private val coinbaseURL = "https://api.pro.coinbase.com/products/${mData?.parse_str}/ticker"
    private val bitstampURL = "https://www.bitstamp.net/api/v2/ticker/${mData?.chart_symbol}/"

    private lateinit var mThread: Thread
    var flag = true

    override fun init() {
        mView.changeUI()
    }

    override fun makeChart() {
        var str3 = "rgba(18,31,48,1)"
        var str2 = "Dark"
        if (!dark_theme) {
            str2 = "Light"
            str3 = "rgba(255,255,255,1)"
        }
        val script = "<!-- TradingView Widget BEGIN -->" +
                "<script type=\"text/javascript\" src=\"https://s3.tradingview.com/tv.js\"></script>" +
                "<script type=\"text/javascript\">new TradingView.widget({" +
                "\"autosize\": true," +
                "\"symbol\": \"BITMEX:$mSymbol\"," +
                "\"interval\": \"60\"," +
                "\"timezone\": \"exchange\"," +
                "\"theme\": \"$str2\"," +
                "\"style\": \"1\"," +
                "\"locale\": \"en\"," +
                "\"toolbar_bg\": \"$str3\"," +
                "\"enable_publishing\": false," +
                "\"hide_top_toolbar\": false," +
                "\"save_image\": false," +
                "\"hide_legend\": true, " +
                "\"studies\": [\"BB@tv-basicstudies\"]," +
                "\"hideideas\": true});</script>" +
                "<!-- TradingView Widget END -->"
        mView.setChart(script)
    }

    override fun initParse() {
        mThread = Thread(Runnable {
            while (flag) {

                val jsonCoinbase = try {
                    changeValue(JSONObject(URL(coinbaseURL).readText()).getDouble("price"))
                } catch (e: Exception) {
                    "No Data"
                }
                val jsonBitstamp = try {
                    changeValue(JSONObject(URL(bitstampURL).readText()).getDouble("last"))
                } catch (e: Exception) {
                    "No Data"
                }

                mView.parseUI(jsonCoinbase, jsonBitstamp)
                sleep(1000)
            }

        })
        mThread.start()
    }


    override fun removeFlag() {
        Log.d("asdf", "thread interrupted")
        flag = false
    }
}