package io.wiffy.bitmexticker.ui.information.detailsFragment

interface DetailsContract {
    interface View{
        fun changeUI()
        fun asyncPre()
        fun updateRecycler(arr:ArrayList<DetailsInfo>)
        fun asyncPost()
    }
    interface Presenter{
        fun init()
    }
}