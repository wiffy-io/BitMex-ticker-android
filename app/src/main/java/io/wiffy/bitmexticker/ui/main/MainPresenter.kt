package io.wiffy.bitmexticker.ui.main

import android.content.Context
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import io.wiffy.bitmexticker.function.changeValue
import io.wiffy.bitmexticker.function.inputComma
import io.wiffy.bitmexticker.model.data.CoinInfo
import io.wiffy.bitmexticker.model.SocketObject as socket
import io.wiffy.bitmexticker.model.Component
import io.wiffy.bitmexticker.model.Component.is_close
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class MainPresenter(private val mView: MainContract.View, con: Context) : MainContract.Presenter {

    private var initCoin = ArrayList<CoinInfo>()
    private val mContext = con
    private var actSymbol: String? = null
    private val coinMarket = "https://api.coinmarketcap.com/v1/global/"

    override fun getCoin(str: String) = Thread(Runnable {
        try {
            val getServer = str.split("\n")
            for (i in 0 until getServer.size) {
                val tmp = getServer[i].split(",")

                val data =
                    CoinInfo(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6])
                initCoin.add(data)
            }
            mView.setRecycler(initCoin)
        } catch (e: Exception) {
        }
    }).start()

    override fun parseInformation() = ArrayList<String>().apply {
        try {
            JSONObject(URL(coinMarket).readText()).let {
                add(
                    inputComma(
                        it.getDouble("total_market_cap_usd")
                    )
                )
                add(
                    "${it.getString("bitcoin_percentage_of_market_cap")}%"
                )
            }
        } catch (e: Exception) {
            add("error")
        }
    }

    override fun changeUI() {
        mView.changeUI()
        setSystemLanguage()
        mView.addSettingActivityChangeListener(View.OnClickListener {
            mView.moveToSetting()
        })
        mView.initInformation()
    }

    override fun makeSocket() =
        with(socket)
        {
            callBack = {
                socketCallback(it)
            }
            sendBack = {
                is_close = false
                socketSubscribe()
            }
            closeBack = {
                this.close()
                mView.changeRecent("---")
                mView.startLoading()
                is_close = true
                Thread(Runnable {
                    try {
                        Thread.sleep(2000)
                        reconnect()
                    } catch (e: Exception) {
                    }
                }).start()
            }
            this.connect()
        }


    override fun socketReconnect() {
        if (is_close) {
            socket.reconnect()
        }
    }

    private fun socketSubscribe() {
        for (i in 0 until initCoin.size) socket.sendMSGFilter("subscribe", "trade", initCoin[i].Symbol.toString())

    }

    private fun socketCallback(it: String) {

        var fuckSymbol: String? = null
        var priceM: String? = null
        for (i in 0 until initCoin.size) {

            val tmpSymbol = initCoin[i].Symbol.toString()

            try {
                val jsonContact = JSONObject(it)

                val tableName = jsonContact.getString("table")


                if (tableName == "tradeBin1m" || tableName == "trade") {
                    val data = jsonContact.getJSONArray("data").getJSONObject(0)

                    val symbol = data.getString("symbol")

                    fuckSymbol = symbol

                    if (symbol == tmpSymbol) {
                        when (tableName) {
                            "tradeBin1m" -> {
                                socket.sendMSGFilter("unsubscribe", "tradeBin1m", tmpSymbol)
                                socket.sendMSGFilter("subscribe", "trade", tmpSymbol)

                                val price = data.getDouble("close")
                                initCoin[i].price = changeValue(price)
                                priceM = changeValue(price)
                                initCoin[i].before_p = "n"
                            }
                            "trade" -> {
                                val price = data.getDouble("price")
                                val side = data.getString("side")
                                if(side == "Buy"){
                                    initCoin[i].before_p = "g"
                                }else if(side == "Sell"){
                                    initCoin[i].before_p = "r"
                                }else{
                                    initCoin[i].before_p = "n"
                                }
                                initCoin[i].price = changeValue(price)
                                priceM = changeValue(price)
                                mView.changeRecent("$symbol : ${changeValue(price)}")

                            }
                        }
                    }
                }
            } catch (e: Exception) {
            }

        }

        Component.infoContext?.let {
            if (fuckSymbol == actSymbol) {
                mView.tossSymbol(priceM!!)
            }
            if (fuckSymbol == "XBTUSD") {
                mView.tossXBT(priceM!!)
            }
        }
        mView.updateRecycler(initCoin)
        mView.stopLoading()
    }


    override fun setSystemLanguage() =
        Handler(Looper.getMainLooper()).post {
            val config = Configuration()
            config.locale = when (Component.global) {
                Locale.KOREAN.toLanguageTag() -> {
                    Locale.KOREAN
                }
                Locale.CHINESE.toLanguageTag() -> {
                    Locale.CHINESE
                }
                Locale.JAPANESE.toLanguageTag() -> {
                    Locale.JAPANESE
                }
                else -> {
                    Locale.ENGLISH
                }
            }
            mContext.resources.updateConfiguration(config, mContext.resources.displayMetrics)
        }

    override fun setSymbol(str: String?) {
        actSymbol = str
    }
}