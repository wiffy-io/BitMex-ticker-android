package com.pale_cosmos.bitmexticker.ui.Information

interface InformationContract {
    interface View{
        fun changeUI()
        fun addTickerButtonListener(listener: android.view.View.OnClickListener)
        fun moveToMain()
    }
    interface Presenter{
        fun init()
    }
}