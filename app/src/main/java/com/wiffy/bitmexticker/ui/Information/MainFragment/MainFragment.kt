package com.wiffy.bitmexticker.ui.Information.MainFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wiffy.bitmexticker.R
import com.wiffy.bitmexticker.extension.get_table_out
import com.wiffy.bitmexticker.model.Util.Companion.dark_theme
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), MainConstract.View {

    lateinit var myView: View
    lateinit var mPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container, false)

        mPresenter = MainPresenter(this, arguments?.getString("symbol"))
        mPresenter.init()
        mPresenter.make_chart()

        return myView
    }

    override fun changeUI() {
        myView.mains.background = resources.getDrawable(get_table_out())
        if (dark_theme) {
            myView.main_view.background = resources.getDrawable(R.drawable.chart_border_dark)
        } else {
            myView.main_view.background = resources.getDrawable(R.drawable.chart_border_light)
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun set_chart(str: String) {
        myView.web_chart.setBackgroundColor(0x01000000)
        myView.web_chart.background = resources.getDrawable(get_table_out())
        myView.web_chart.settings?.javaScriptEnabled = true
        myView.web_chart.loadDataWithBaseURL("", str, "text/html", "UTF-8", "");
        //Log.d("asdf",str)
    }

}