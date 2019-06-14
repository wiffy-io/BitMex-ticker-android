package com.pale_cosmos.bitmexticker.ui.Information.MainFragment

interface MainConstract {
    interface View{
        fun changeUI()
        fun set_chart(str:String)
    }
    interface Presenter{
        fun init()
        fun make_chart()
    }
}