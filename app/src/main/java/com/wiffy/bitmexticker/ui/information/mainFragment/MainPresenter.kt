package com.wiffy.bitmexticker.ui.information.mainFragment

import com.wiffy.bitmexticker.model.Util.Companion.dark_theme


class MainPresenter(act:MainContract.View, symbol: String?):MainContract.Presenter {


    private val mView = act
    private val mSymbol = symbol

    override fun init() {
        mView.changeUI()
    }

    override fun makeChart() {
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

}