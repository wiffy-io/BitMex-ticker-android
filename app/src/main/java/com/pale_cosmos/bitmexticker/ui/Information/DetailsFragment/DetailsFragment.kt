package com.pale_cosmos.bitmexticker.ui.Information.DetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pale_cosmos.bitmexticker.R

class DetailsFragment:Fragment(),DetailsConstract.View {
    lateinit var myView:View
    lateinit var mPresenter: DetailsPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_details, container,false)

        return myView
    }
}