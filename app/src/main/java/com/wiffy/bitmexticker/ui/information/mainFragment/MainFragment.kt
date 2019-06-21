package com.wiffy.bitmexticker.ui.information.mainFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.changeValue
import com.wiffy.bitmexticker.extension.getTableOut
import com.wiffy.bitmexticker.extension.getTitle2
import com.wiffy.bitmexticker.model.CoinInfo
import com.wiffy.bitmexticker.model.Util.Companion.dark_theme
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.lang.Exception

class MainFragment : Fragment(), MainContract.View {

    lateinit var myView: View
    lateinit var mPresenter: MainPresenter
    var symbol: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container, false)

        symbol = (arguments?.getSerializable("data") as CoinInfo).Symbol
        setPrice((arguments?.getSerializable("data") as CoinInfo).price!!)
        setXBT(arguments?.getString("xbt")!!)


        mPresenter = MainPresenter(this, arguments?.getSerializable("data") as CoinInfo)
        mPresenter.init()
        mPresenter.initParse()
        mPresenter.makeChart()

        return myView
    }

    override fun changeUI() {
        myView.mains.background = resources.getDrawable(getTableOut())
        myView.bitstamp.setTextColor(ContextCompat.getColorStateList(context!!, getTitle2()))
        myView.coinbase.setTextColor(ContextCompat.getColorStateList(context!!, getTitle2()))

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
            if (bitstamp != "No Data") {
                (myView.bitstamp_p[0] as TextView).text = bitstamp
                myView.bitstamp_pp.text = make_pre(bitstamp, true) + " %"
            }

            if (coinbase != "No Data") {
                (myView.coinbase_p[0] as TextView).text = coinbase
                myView.coinbase_pp.text = make_pre(coinbase, false) + " %"
            }

        }

    }

    override fun onDetach() {
        mPresenter.removeFlag()
        super.onDetach()
    }


    private fun make_pre(str: String, flag: Boolean): String {
        val value = (xbtPrice.toDouble() - str.toDouble()) / str.toDouble() * 100
        if(context ==null)return ""

        return try {
            when {
                value < 0 -> {
                    if (flag) {
                        Handler(Looper.getMainLooper()).post {
                            myView.bitstamp_ppap.setCardBackgroundColor(
                                ContextCompat.getColorStateList(
                                    context!!,
                                    R.color.red_tr
                                )
                            )
                            myView.bitstamp_pp.setTextColor(ContextCompat.getColorStateList(context!!, R.color.red))
                        }
                    } else {
                        Handler(Looper.getMainLooper()).post {
                            myView.coinbase_ppap.setCardBackgroundColor(
                                ContextCompat.getColorStateList(
                                    context!!,
                                    R.color.red_tr
                                )
                            )
                            myView.coinbase_pp.setTextColor(ContextCompat.getColorStateList(context!!, R.color.red))
                        }
                    }
                    String.format("%.2f", value)
                }
                value == 0.00 -> {
                    if (flag) {
                        Handler(Looper.getMainLooper()).post {
                            myView.bitstamp_ppap.setCardBackgroundColor(
                                ContextCompat.getColorStateList(
                                    context!!,
                                    R.color.cardBack
                                )
                            )
                            myView.bitstamp_pp.setTextColor(
                                ContextCompat.getColorStateList(
                                    context!!,
                                    R.color.cardText
                                )
                            )
                        }
                    } else {
                        Handler(Looper.getMainLooper()).post {
                            myView.coinbase_ppap.setCardBackgroundColor(
                                ContextCompat.getColorStateList(
                                    context!!,
                                    R.color.cardBack
                                )
                            )
                            myView.coinbase_pp.setTextColor(
                                ContextCompat.getColorStateList(
                                    context!!,
                                    R.color.cardText
                                )
                            )
                        }
                    }
                    String.format("%.2f", value)
                }
                else -> {
                    if (flag) {
                        Handler(Looper.getMainLooper()).post {
                            myView.bitstamp_ppap.setCardBackgroundColor(
                                ContextCompat.getColorStateList(context!!, R.color.green_tr)
                            )
                            myView.bitstamp_pp.setTextColor(ContextCompat.getColorStateList(context!!, R.color.green))
                        }
                    } else {
                        Handler(Looper.getMainLooper()).post {
                            myView.coinbase_ppap.setCardBackgroundColor(
                                ContextCompat.getColorStateList(
                                    context!!,
                                    R.color.green_tr
                                )
                            )
                            myView.coinbase_pp.setTextColor(ContextCompat.getColorStateList(context!!, R.color.green))
                        }
                    }
                    "+${String.format("%.2f", value)}"
                }
            }
        } catch (e: Exception) {
            ""
        }
    }

    private var xbtPrice: String = "0"

    fun setXBT(str: String) {
        if (!symbol?.contains("USD")!! && xbtPrice != "0") {
            myView.sub_price.text = "≈ ${changeValue(str.toDouble() * xbtPrice.toDouble())} $"
        }
    }

    fun setPrice(str: String) {
        if (symbol?.contains("USD")!!) {
            myView.sub_price.text = "≈ $str $"
        }
        xbtPrice = str
        myView.main_price.text = str
    }
}