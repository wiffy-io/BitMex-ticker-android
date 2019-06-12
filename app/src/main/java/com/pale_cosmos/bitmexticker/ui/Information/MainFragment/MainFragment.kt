package com.pale_cosmos.bitmexticker.ui.Information.MainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_fragment_background
import com.pale_cosmos.bitmexticker.extension.get_table_out

class MainFragment: Fragment(),MainConstract.View {
    lateinit var myView:View
    lateinit var mPresenter:MainPresenter
    lateinit var parentLayout: RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container,false)
        mPresenter = MainPresenter(this)
        mPresenter.init()

        return myView
    }

    override fun changeUI() {
        parentLayout=myView.findViewById(R.id.mains)
        parentLayout.background = resources.getDrawable(get_fragment_background())
    }
}