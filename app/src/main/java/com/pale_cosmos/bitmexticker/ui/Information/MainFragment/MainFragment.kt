package com.pale_cosmos.bitmexticker.ui.Information.MainFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_fragment_background
import com.pale_cosmos.bitmexticker.extension.get_navi
import com.pale_cosmos.bitmexticker.extension.get_table_in

class MainFragment: Fragment(),MainConstract.View {


    lateinit var myView:View
    lateinit var mPresenter:MainPresenter
    lateinit var parentLayout: RelativeLayout
    lateinit var mainLayout: RelativeLayout

    var web_chart: WebView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container,false)
        web_chart = myView.findViewById(R.id.web_chart)
        mainLayout = myView.findViewById(R.id.mains)

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
        mainLayout?.setBackgroundColor(get_table_in())
        mainLayout?.background = resources.getDrawable(get_table_in())
        web_chart?.setBackgroundResource(0x00000000)
        web_chart?.background = resources.getDrawable(get_table_in())
        web_chart?.getSettings()?.setJavaScriptEnabled(true);
        web_chart?.loadDataWithBaseURL("", str, "text/html", "UTF-8", "");
        //Log.d("asdf",str)
    }
}