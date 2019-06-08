package com.pale_cosmos.bitmexticker.ui.Information

interface InformationContract {
    interface View{
        fun changeUI()
    }
    interface Presenter{
        fun init()
    }
}