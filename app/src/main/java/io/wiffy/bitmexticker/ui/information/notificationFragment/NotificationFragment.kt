package io.wiffy.bitmexticker.ui.information.notificationFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.changeValue
import io.wiffy.bitmexticker.extension.getFragmentBackground
import io.wiffy.bitmexticker.extension.getTableOut
import io.wiffy.bitmexticker.model.CoinInfo
import io.wiffy.bitmexticker.model.Util.Companion.dark_theme
import kotlinx.android.synthetic.main.fragment_notification.view.*

class NotificationFragment : Fragment(), NotificationContract.View {
    lateinit var myView: View
    lateinit var mPresenter: NotificationPresenter
    lateinit var parentLayout: RelativeLayout
    var symbol: String? = null
    private var xbtPrice: String = "0"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_notification, container, false)
        symbol = (arguments?.getSerializable("data") as CoinInfo).Symbol
        setPrice((arguments?.getSerializable("data") as CoinInfo).price!!)
        setXBT(arguments?.getString("xbt")!!)
        mPresenter = NotificationPresenter(this)
        mPresenter.init()

        return myView
    }

    override fun changeUI() {
        myView.notis.background = resources.getDrawable(getTableOut())
        parentLayout = myView.findViewById(R.id.notis)
        parentLayout.background = resources.getDrawable(getFragmentBackground())

        val color =
        if (dark_theme) { resources.getDrawable(R.drawable.chart_border_dark)
        } else {
           resources.getDrawable(R.drawable.chart_border_light)
        }
        myView.noti_view.background = color
        myView.noti_context.background = color
    }

    @SuppressLint("SetTextI18n")
    fun setXBT(str: String) {
        try {
            if (!symbol?.contains("USD")!! && xbtPrice != "0") {
                myView.noti_sub_price.text = "≈ ${changeValue(str.toDouble() * xbtPrice.toDouble())} $"
            }
        } catch (e: Exception) {
        }
    }

    @SuppressLint("SetTextI18n")
    fun setPrice(str: String) {
        try {
            if (symbol?.contains("USD")!!) {
                myView.noti_sub_price.text = "≈ $str $"
            }
            xbtPrice = str
            myView.noti_price.text = str
        } catch (e: Exception) {
        }
    }
}