package com.wiffy.bitmexticker.ui.Information.NotificationFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.get_fragment_background

class NotificationFragment: Fragment(),NotificationConstract.View {
    lateinit var myView: View
    lateinit var mPresenter:NotificationPresenter
    lateinit var parentLayout: RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_notification, container,false)
        mPresenter = NotificationPresenter(this)
        mPresenter.init()

        return myView
    }

    override fun changeUI() {
        parentLayout=myView.findViewById(R.id.mains)
        parentLayout.background = resources.getDrawable(get_fragment_background())
    }
}