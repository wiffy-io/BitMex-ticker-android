package com.pale_cosmos.bitmexticker.ui.Information.MainFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_fragment_background

class MainFragment: Fragment(),MainConstract.View {


    lateinit var myView:View
    lateinit var mPresenter:MainPresenter
    lateinit var parentLayout: RelativeLayout

    var web_chart: WebView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container,false)
        web_chart = myView.findViewById(R.id.web_chart)

        mPresenter = MainPresenter(this,arguments?.getString("symbol"))
        mPresenter.init()
        mPresenter.make_chart()

        return myView
    }

    override fun changeUI() {
        parentLayout=myView.findViewById(R.id.mains)
        parentLayout.background = resources.getDrawable(get_fragment_background())
    }

    override fun set_chart(str:String) {
        web_chart?.getSettings()?.setJavaScriptEnabled(true);
        web_chart?.loadDataWithBaseURL("", str, "text/html", "UTF-8", "");
        //Log.d("asdf",str)
    }
}