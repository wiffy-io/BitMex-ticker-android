package io.wiffy.bitmexticker.ui.information.mainFragment

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
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.changeValue
import io.wiffy.bitmexticker.function.getTableOut
import io.wiffy.bitmexticker.function.getTitle2
import io.wiffy.bitmexticker.model.data.CoinInfo
import io.wiffy.bitmexticker.model.Util.dark_theme
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : MainContract.View() {

    lateinit var myView: View
    lateinit var mPresenter: MainPresenter
    var symbol: String? = null
    private var xbtPrice: String = "0"

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
        myView.web_chart.loadDataWithBaseURL("", str, "text/html", "UTF-8", "")
    }

    override fun parseUI(coinBase: String, bitStamp: String) = context?.let {
        Handler(Looper.getMainLooper()).post {
            if (bitStamp != "No Data") {
                (myView.bitstamp_p[0] as TextView).text = bitStamp
                myView.bitstamp_pp.text = parseBitStamp(bitStamp)
            }
            if (coinBase != "No Data") {
                (myView.coinbase_p[0] as TextView).text = coinBase
                myView.coinbase_pp.text = parseCoinBase(coinBase)
            }
        }
    }


    override fun parseBitStamp(str: String): String {
        try {
            val value = (xbtPrice.toDouble() - str.toDouble()) / str.toDouble() * 100
            return when {
                value > 0 -> {
                    Handler(Looper.getMainLooper()).post {
                        myView.bitstamp_ppap.setCardBackgroundColor(
                            ContextCompat.getColorStateList(context!!, R.color.green_tr)
                        )
                        myView.bitstamp_pp.setTextColor(ContextCompat.getColorStateList(context!!, R.color.green))
                    }
                    "+${String.format("%.2f", value)}%"
                }

                value == 0.0 -> {
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
                    "${String.format("%.2f", value)}%"
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        myView.bitstamp_ppap.setCardBackgroundColor(
                            ContextCompat.getColorStateList(
                                context!!,
                                R.color.red_tr
                            )
                        )
                        myView.bitstamp_pp.setTextColor(
                            ContextCompat.getColorStateList(
                                context!!, R.color.red
                            )
                        )
                    }
                    "${String.format("%.2f", value)}%"
                }
            }
        } catch (e: Exception) {
            return ""
        }

    }

    override fun parseCoinBase(str: String): String {
        val value = (xbtPrice.toDouble() - str.toDouble()) / str.toDouble() * 100
        return when {
            value > 0 -> {
                Handler(Looper.getMainLooper()).post {
                    myView.coinbase_ppap.setCardBackgroundColor(
                        ContextCompat.getColorStateList(context!!, R.color.green_tr)
                    )
                    myView.coinbase_pp.setTextColor(ContextCompat.getColorStateList(context!!, R.color.green))
                }
                "+${String.format("%.2f", value)}%"
            }

            value == 0.0 -> {
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
                "${String.format("%.2f", value)}%"
            }
            else -> {
                Handler(Looper.getMainLooper()).post {
                    myView.coinbase_ppap.setCardBackgroundColor(
                        ContextCompat.getColorStateList(
                            context!!,
                            R.color.red_tr
                        )
                    )
                    myView.coinbase_pp.setTextColor(
                        ContextCompat.getColorStateList(
                            context!!, R.color.red
                        )
                    )
                }
                "${String.format("%.2f", value)}%"
            }
        }
    }

    override fun onDetach() {
        mPresenter.removeFlag()
        super.onDetach()
    }


    @SuppressLint("SetTextI18n")
    fun setXBT(str: String) {
        if (!symbol?.contains("USD")!! && xbtPrice != "0") {
            myView.sub_price.text = "≈ ${changeValue(str.toDouble() * xbtPrice.toDouble())} $"
        }
    }

    @SuppressLint("SetTextI18n")
    fun setPrice(str: String) {
        if (symbol?.contains("USD")!!) {
            myView.sub_price.text = "≈ $str $"
        }
        xbtPrice = str
        myView.main_price.text = str
    }
}