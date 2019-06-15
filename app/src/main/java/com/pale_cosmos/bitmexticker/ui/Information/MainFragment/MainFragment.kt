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
import com.pale_cosmos.bitmexticker.extension.get_table_out
import com.pale_cosmos.bitmexticker.model.Util.Companion.dark_theme
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment: Fragment(),MainConstract.View {

    lateinit var myView:View
    lateinit var mPresenter:MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container,false)

        mPresenter = MainPresenter(this,arguments?.getString("symbol"))
        mPresenter.init()
        mPresenter.make_chart()

        return myView
    }

    override fun changeUI() {
        myView.mains.background = resources.getDrawable(get_table_out())
        if(dark_theme){
            myView.main_view.background = resources.getDrawable(R.drawable.chart_border_dark)
        }else{
            myView.main_view.background = resources.getDrawable(R.drawable.chart_border_light)
        }

    }

    override fun set_chart(str:String) {
        myView.web_chart.setBackgroundColor(0x01000000);
        myView.web_chart.background = resources.getDrawable(get_table_out())
        myView.web_chart.getSettings()?.setJavaScriptEnabled(true);
        myView.web_chart.loadDataWithBaseURL("", str, "text/html", "UTF-8", "");
        //Log.d("asdf",str)
    }

}