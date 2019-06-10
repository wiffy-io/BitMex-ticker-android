package com.pale_cosmos.bitmexticker.ui.Information.MainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pale_cosmos.bitmexticker.R

class MainFragment: Fragment(),MainConstract.View {
    lateinit var myView:View
    lateinit var mPresenter:MainPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container,false)
        mPresenter = MainPresenter(this)
        mPresenter.init()

        return myView
    }

    override fun changeUI() {

    }
}