package com.pale_cosmos.bitmexticker.ui.Information.OrderBookFragment

class OrderBookPresenter(act: OrderBookConstract.View) : OrderBookConstract.Presenter {
    val mView = act
    lateinit var arr:ArrayList<OrderBook_info>
    override fun init() {
        mView.changeUI()
        arr = ArrayList()
        //test
        arr.add(OrderBook_info("0111","0223","230"))
        arr.add(OrderBook_info("4441","231","2221"))
        arr.add(OrderBook_info("23232","44442","222"))
        arr.add(OrderBook_info("0111","0223","230"))
        arr.add(OrderBook_info("4441","231","2221"))
        arr.add(OrderBook_info("23232","44442","222"))
        arr.add(OrderBook_info("0111","0223","230"))
        arr.add(OrderBook_info("4441","231","2221"))
        arr.add(OrderBook_info("23232","44442","222"))
        arr.add(OrderBook_info("0111","0223","230"))
        arr.add(OrderBook_info("4441","231","2221"))
        arr.add(OrderBook_info("23232","44442","222"))
        arr.add(OrderBook_info("0111","0223","230"))
        arr.add(OrderBook_info("4441","231","2221"))
        arr.add(OrderBook_info("23232","44442","222"))
        arr.add(OrderBook_info("0111","0223","230"))
        arr.add(OrderBook_info("4441","231","2221"))
        arr.add(OrderBook_info("23232","44442","222"))
        arr.add(OrderBook_info("0111","0223","230"))
        arr.add(OrderBook_info("4441","231","2221"))
        arr.add(OrderBook_info("23232","44442","222"))
        arr.add(OrderBook_info("0111","0223","230"))
        arr.add(OrderBook_info("4441","231","2221"))
        arr.add(OrderBook_info("23232","44442","222"))

        //test
        mView.update_recycler(arr)
    }
}