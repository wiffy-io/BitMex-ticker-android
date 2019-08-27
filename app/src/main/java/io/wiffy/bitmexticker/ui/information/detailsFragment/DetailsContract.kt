package io.wiffy.bitmexticker.ui.information.detailsFragment

import io.wiffy.bitmexticker.model.SuperContract
import io.wiffy.bitmexticker.ui.information.detailsFragment.tool.DetailsInfo

interface DetailsContract {
    abstract class View : SuperContract.SuperFragment() {
        abstract fun changeUI()
        abstract fun asyncPre()
        abstract fun updateRecycler(arr: ArrayList<DetailsInfo>)
        abstract fun asyncPost()
    }

    interface Presenter : SuperContract.WiffyObject {
        fun init()
    }
}