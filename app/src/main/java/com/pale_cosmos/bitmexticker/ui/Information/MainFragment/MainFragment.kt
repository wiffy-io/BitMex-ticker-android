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
import com.pale_cosmos.bitmexticker.extension.get_table_out
import com.pale_cosmos.bitmexticker.extension.get_table_out_parsed

class MainFragment : Fragment(), MainConstract.View {


    lateinit var myView: View
    lateinit var mPresenter: MainPresenter
    lateinit var parentLayout: RelativeLayout

    lateinit var web_chart: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_main, container, false)
        web_chart = myView.findViewById(R.id.web_chart)

        mPresenter = MainPresenter(this, arguments?.getString("symbol"))
        mPresenter.init()
        mPresenter.make_chart()

        return myView
    }

    override fun changeUI() {
        parentLayout = myView.findViewById(R.id.mains)
        parentLayout.background = resources.getDrawable(get_fragment_background())
        web_chart.setBackgroundColor(get_table_out_parsed())
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun set_chart(str: String) {
        web_chart.settings.javaScriptEnabled = true
        web_chart.loadDataWithBaseURL("", str, "text/html", "UTF-8", "")
    }
}