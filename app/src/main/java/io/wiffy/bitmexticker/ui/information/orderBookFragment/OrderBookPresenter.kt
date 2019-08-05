package io.wiffy.bitmexticker.ui.information.orderBookFragment

import io.wiffy.bitmexticker.extension.changeValue
import io.wiffy.bitmexticker.model.MyApplication.Companion.socket
import io.wiffy.bitmexticker.ui.information.orderBookFragment.tool.OrderBookInfo
import org.json.JSONObject
import java.lang.Exception

class OrderBookPresenter(act: OrderBookConstract.View, sym: String) : OrderBookConstract.Presenter {

    val mView = act
    lateinit var arr: ArrayList<OrderBookInfo>
    private var symbol = sym

    override fun init() =
        with(mView)
        {
            changeUI()
            setRecycler()
            startLoading()
        }


    override fun startWs() {
        makeSocket()
    }

    override fun stopWs() {
        socket.sendMSGFilter("unsubscribe", "orderBook10", symbol)
    }

    private fun makeSocket() =
        with(socket)
        {
            sendMSGFilter("subscribe", "orderBook10", symbol)
            callBack = {
                socketCallback(it)
            }
        }


    private fun socketCallback(it: String) {
        if (it.contains("data") && it.contains("orderBook10")) {
            arr = ArrayList()
            try {
                val jsonContact = JSONObject(it)
                val data = jsonContact.getJSONArray("data")
                val bids = data.getJSONObject(0).getJSONArray("bids")
                val asks = data.getJSONObject(0).getJSONArray("asks")

                for (x in asks.length() - 1 downTo 0) {
                    arr.add(
                        OrderBookInfo(
                            asks.getJSONArray(x)[1].toString(),
                            changeValue(asks.getJSONArray(x)[0].toString().toDouble()),
                            null
                        )
                    )
                }
                for (x in 0 until bids.length()) {
                    arr.add(
                        OrderBookInfo(
                            null,
                            changeValue(bids.getJSONArray(x)[0].toString().toDouble()),
                            bids.getJSONArray(x)[1].toString()
                        )
                    )
                }
                mView.updateRecycler(arr)
                mView.stopLoading()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (it.contains("trade")) {
            val jsonContact = JSONObject(it)
            val tableName = jsonContact.getString("table")
            if (tableName == "trade") {
                val data = jsonContact.getJSONArray("data").getJSONObject(0)
                val symbol2 = data.getString("symbol")
                if (symbol2 == symbol) {
                    val price = data.getDouble("price")
                    val size = data.getDouble("size")
                    mView.changeRecent("$symbol2 : ${changeValue(price)} - $size")
                }
            }
        }
    }


}