package io.wiffy.bitmexticker.ui.information.orderBookFragment

import io.wiffy.bitmexticker.function.changeValue
import io.wiffy.bitmexticker.model.Component.orderCount
import io.wiffy.bitmexticker.model.SocketObject as socket
import io.wiffy.bitmexticker.ui.information.orderBookFragment.tool.OrderBookInfo
import org.json.JSONObject
import java.lang.Exception

class OrderBookPresenter(private val mView: OrderBookContract.View, private var symbol: String) :
    OrderBookContract.Presenter {

    lateinit var arr: ArrayList<OrderBookInfo>

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
                if (orderCount >= 20) {
                    orderCount = 0
                    var sum = 0

                    val data = JSONObject(it).getJSONArray("data")

                    val bids = data.getJSONObject(0).getJSONArray("bids")
                    val asks = data.getJSONObject(0).getJSONArray("asks")

                    for (x in asks.length() - 1 downTo 0) {
                        val myValue = asks.getJSONArray(x)[1] as Int
                        arr.add(
                            OrderBookInfo(
                                myValue.toString(),
                                changeValue(asks.getJSONArray(x)[0].toString().toDouble()),
                                null
                            )
                        )
                        sum += myValue
                    }
                    for (x in 0 until bids.length()) {
                        val myValue = bids.getJSONArray(x)[1] as Int
                        arr.add(
                            OrderBookInfo(
                                null,
                                changeValue(bids.getJSONArray(x)[0].toString().toDouble()),
                                myValue.toString()
                            )
                        )
                        sum += myValue
                    }
                    mView.updateRecycler(arr, sum)
                    mView.stopLoading()
                } else {
                    console("cccc=$orderCount")
                }
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