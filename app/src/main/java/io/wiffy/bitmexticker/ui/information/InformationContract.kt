package io.wiffy.bitmexticker.ui.information

interface InformationContract {
    interface View {
        fun changeUI()
        fun addTickerButtonListener(listener: android.view.View.OnClickListener)
        fun moveToMain()
        fun initFragment()
        fun viewFragmentDetails()
        fun viewFragmentMain()
        fun viewFragmentOrderBook()
        fun viewFragmentNotification()
    }

    interface Presenter {
        fun init()
        fun setSystemLanguage(): Boolean
    }
}