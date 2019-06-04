package com.pale_cosmos.bitmexticker.ui.Main



interface MainContract {
    interface View{
        fun changeStatusBar(color:Int)
        fun append_text(str:String)
    }
    interface Presenter{
        fun change_UI()
        fun make_socket()
    }
}