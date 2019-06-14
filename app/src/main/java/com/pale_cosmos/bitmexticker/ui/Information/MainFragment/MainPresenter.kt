package com.pale_cosmos.bitmexticker.ui.Information.MainFragment

import android.graphics.Color
import com.pale_cosmos.bitmexticker.extension.get_table_in
import androidx.annotation.ColorInt
import android.R.color
import com.pale_cosmos.bitmexticker.model.Util.Companion.dark_theme


class MainPresenter(act:MainConstract.View, symbol: String?):MainConstract.Presenter {


    private val mView = act
    private val symbol = symbol

    override fun init() {
        mView.changeUI()
    }

    override fun make_chart() {
        var str3 = "rgba(18,31,48,1)"
        var str2 = "Dark"
        if (!dark_theme){
            str2 = "Light"
            str3 = "rgba(255,255,255,1)"
        }
        var script = "<!-- TradingView Widget BEGIN -->" +
                "<script type=\"text/javascript\" src=\"https://s3.tradingview.com/tv.js\"></script>" +
                "<script type=\"text/javascript\">new TradingView.widget({" +
                "\"autosize\": true," +
                "\"symbol\": \"BITMEX:${symbol}\"," +
                "\"interval\": \"60\"," +
                "\"timezone\": \"exchange\"," +
                "\"theme\": \"${str2}\"," +
                "\"style\": \"1\"," +
                "\"locale\": \"en\"," +
                "\"toolbar_bg\": \"${str3}\"," +
                "\"enable_publishing\": false," +
                "\"hide_top_toolbar\": false," +
                "\"save_image\": false," +
                "\"hide_legend\": true, " +
                "\"studies\": [\"BB@tv-basicstudies\"]," +
                "\"hideideas\": true});</script>" +
                "<!-- TradingView Widget END -->"
        mView.set_chart(script)
    }

}