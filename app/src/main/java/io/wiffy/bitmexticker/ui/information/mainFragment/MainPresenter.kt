package io.wiffy.bitmexticker.ui.information.mainFragment

import io.wiffy.bitmexticker.function.changeValue
import io.wiffy.bitmexticker.model.data.CoinInfo
import io.wiffy.bitmexticker.model.Component.dark_theme
import org.json.JSONObject
import java.lang.Exception
import java.lang.Thread.sleep
import java.net.URL


class MainPresenter(private val mView: MainContract.View, mData: CoinInfo?) :
    MainContract.Presenter {

    private val mSymbol = mData?.Symbol
    private val coinBaseURL = "https://api.pro.coinbase.com/products/${mData?.parse_str}/ticker"
    private val bitStampURL = "https://www.bitstamp.net/api/v2/ticker/${mData?.chart_symbol}/"
    var flag = true

    override fun init() = mView.changeUI()


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

    override fun initParse() = Thread(Runnable {
        while (flag) {

            mView.parseUI(
                try {
                    changeValue(JSONObject(URL(coinBaseURL).readText()).getDouble("price"))
                } catch (e: Exception) {
                    "No Data"
                }, try {
                    changeValue(JSONObject(URL(bitStampURL).readText()).getDouble("last"))
                } catch (e: Exception) {
                    "No Data"
                }
            )
            sleep(1000)
        }
    }).start()


    override fun removeFlag() {
        flag = false
    }
}