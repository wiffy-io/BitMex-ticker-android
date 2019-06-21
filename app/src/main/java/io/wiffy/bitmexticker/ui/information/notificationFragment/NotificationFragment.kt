package io.wiffy.bitmexticker.ui.information.notificationFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.getFragmentBackground

class NotificationFragment: Fragment(),NotificationContract.View {
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
        parentLayout.background = resources.getDrawable(getFragmentBackground())
    }
}