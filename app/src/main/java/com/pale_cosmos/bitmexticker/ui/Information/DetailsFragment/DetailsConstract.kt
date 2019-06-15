package com.pale_cosmos.bitmexticker.ui.Information.DetailsFragment

interface DetailsConstract {
    interface View{
        fun changeUI()
        fun async_pre()
        fun update_recycler(arr:ArrayList<Details_info>)
        fun async_post()
    }
    interface Presenter{
        fun init()
    }
    interface Task{
    }
}