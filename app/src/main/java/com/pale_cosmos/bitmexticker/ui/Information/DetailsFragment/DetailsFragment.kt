package com.pale_cosmos.bitmexticker.ui.Information.DetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_fragment_background

class DetailsFragment : Fragment(), DetailsConstract.View {
    lateinit var myView: View
    lateinit var mPresenter: DetailsPresenter
    lateinit var parentLayout: RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_details, container, false)
        mPresenter = DetailsPresenter(this)
        mPresenter.init()
        return myView
    }

    override fun changeUI() {
        parentLayout=myView.findViewById(R.id.details)
        parentLayout.background = resources.getDrawable(get_fragment_background())
    }
}