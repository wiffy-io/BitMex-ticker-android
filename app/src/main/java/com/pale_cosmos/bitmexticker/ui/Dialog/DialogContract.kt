package com.pale_cosmos.bitmexticker.ui.Dialog

import android.view.View

interface DialogContract {
    interface View{
        fun moveToBack()
        fun addListenerOkButton(listener:android.view.View.OnClickListener)
    }

    interface Presenter{
        fun init()
    }
}