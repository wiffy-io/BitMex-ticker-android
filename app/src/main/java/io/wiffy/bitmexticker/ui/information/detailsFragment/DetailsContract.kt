package io.wiffy.bitmexticker.ui.information.detailsFragment

import androidx.fragment.app.Fragment
import io.wiffy.bitmexticker.ui.information.detailsFragment.tool.DetailsInfo

interface DetailsContract {
    abstract class View : Fragment() {
        abstract fun changeUI()
        abstract fun asyncPre()
        abstract fun updateRecycler(arr: ArrayList<DetailsInfo>)
        abstract fun asyncPost()
    }

    interface Presenter {
        fun init()
    }
}