package com.pale_cosmos.bitmexticker.ui.Information

interface InformationContract {
    interface View{
        fun changeUI()
        fun addTickerButtonListener(listener: android.view.View.OnClickListener)
        fun moveToMain()
        fun initFragment()
        fun viewFragment_Details()
        fun viewFragment_Main()
        fun viewFragment_OrderBook()
        fun viewFragment_Notification()
    }
    interface Presenter{
        fun init()
    }
}