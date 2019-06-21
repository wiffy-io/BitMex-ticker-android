package com.wiffy.bitmexticker.ui.information.mainFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.changeValue
import com.wiffy.bitmexticker.extension.getTableOut
import com.wiffy.bitmexticker.model.CoinInfo
import com.wiffy.bitmexticker.model.Util
import com.wiffy.bitmexticker.model.Util.Companion.dark_theme
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), MainContract.View {

    lateinit var myView: View
    lateinit var mPresenter: MainPresenter
    var symbol:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container, false)

        symbol = (arguments?.getSerializable("data") as CoinInfo).Symbol
        mPresenter = MainPresenter(this, arguments?.getSerializable("data") as CoinInfo)
        mPresenter.init()
        mPresenter.initParse()
        mPresenter.makeChart()

        return myView
    }

    override fun changeUI() {
        myView.mains.background = resources.getDrawable(getTableOut())
        if (dark_theme) {
            myView.main_view.background = resources.getDrawable(R.drawable.chart_border_dark)
        } else {
            myView.main_view.background = resources.getDrawable(R.drawable.chart_border_light)
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setChart(str: String) {
        myView.web_chart.setBackgroundColor(0x01000000)
        myView.web_chart.background = resources.getDrawable(getTableOut())
        myView.web_chart.settings?.javaScriptEnabled = true
        myView.web_chart.loadDataWithBaseURL("", str, "text/html", "UTF-8", "");
    }

    override fun parseUI(coinbase: String, bitstamp: String) {
        Handler(Looper.getMainLooper()).post {
            (myView.bitstamp_p[0] as TextView).text = bitstamp
            (myView.coinbase_p[0] as TextView).text = coinbase
        }
//        Log.d("asdf", coinbase)
    }

    override fun onDetach() {
        mPresenter.removeFlag()
        super.onDetach()
    }

    private var xbtPrice:String = "0"

    fun setXBT(str:String){
        if(!symbol?.contains("USD")!! && xbtPrice != "0"){
            var aa = str.toDouble() * xbtPrice.toDouble()
            sub_price.text = changeValue(aa)
        }
    }

    fun setPrice(str:String){
        if(symbol?.contains("USD")!!){
            sub_price.text = str
        }
        xbtPrice = str
        main_price.text = str
    }
}