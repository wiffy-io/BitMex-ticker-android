package io.wiffy.bitmexticker.ui.information.notificationFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.messaging.FirebaseMessaging
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.changeValue
import io.wiffy.bitmexticker.extension.getFragmentBackground
import io.wiffy.bitmexticker.extension.getTableOut
import io.wiffy.bitmexticker.model.CoinInfo
import io.wiffy.bitmexticker.model.Util
import io.wiffy.bitmexticker.model.Util.Companion.dark_theme
import io.wiffy.bitmexticker.model.VerticalSpaceItemDecoration
import io.wiffy.bitmexticker.ui.information.InformationActivity
import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.InformationComare
import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.NotificationAdapter
import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.NotificationInfo
import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.NotificationTask
import kotlinx.android.synthetic.main.fragment_notification.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotificationFragment : Fragment(), NotificationContract.View {
    lateinit var myView: View
    lateinit var mPresenter: NotificationPresenter
    lateinit var parentLayout: RelativeLayout
    var symbol: String? = null
    private var xbtPrice: String = "0"
    var myAdapter: NotificationAdapter? = null
    lateinit var myList: ArrayList<NotificationInfo>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_notification, container, false)
        symbol = (arguments?.getSerializable("data") as CoinInfo).Symbol
        setPrice((arguments?.getSerializable("data") as CoinInfo).price!!)
        setXBT(arguments?.getString("xbt")!!)
        mPresenter = NotificationPresenter(this)
        mPresenter.init()

        return myView
    }

    @SuppressLint("SimpleDateFormat")
    override fun changeUI() {
        myList = ArrayList()

        if (Util.noticom != null)
            for (x in Util.noticom?.iterator()!!) {
                val y = x.split(":")
                myList.add(NotificationInfo(y[0], y[1], y[2]))
            }
        Collections.sort(myList, InformationComare())
        myAdapter = NotificationAdapter(
            myList,
            context!!,
            activity as InformationActivity
        )
        myView.noticycle.adapter = myAdapter
        myView.noticycle.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
        myView.notis.background = resources.getDrawable(getTableOut())
        parentLayout = myView.findViewById(R.id.notis)
        parentLayout.background = resources.getDrawable(getFragmentBackground())


        val verbose = if (dark_theme) {
            resources.getColor(R.color.WHITE)
        } else {
            resources.getColor(R.color.BLACK)
        }

        if (dark_theme) {
            myView.angimotti.background = resources.getDrawable(R.drawable.chart_border_dark)
            myView.noti_view.background = resources.getDrawable(R.drawable.chart_border_dark)
            myView.noti_context.background = resources.getDrawable(R.drawable.chart_border_dark)
        } else {
            myView.noti_view.background = resources.getDrawable(R.drawable.chart_border_light)
            myView.noti_context.background = resources.getDrawable(R.drawable.chart_border_light)
            myView.angimotti.background = resources.getDrawable(R.drawable.chart_border_light)
        }
        myView.noticycle.addItemDecoration(VerticalSpaceItemDecoration(2))
        myView.noticycle.setBackgroundColor(resources.getColor(getTableOut()))
        myView.cdcd123.setOnClickListener {
            var flag = true
            val text = myView.texter.text.toString()

            try {
                for (v in myList) {
                    if (v.value?.toDouble() == text.toDouble()) {
                        flag = false
                        break
                    }
                }
                if (flag) {
                    var numbers = text
                    if (numbers.toInt().toDouble() == numbers.toDouble())
                        numbers = numbers.toInt().toString()
                    val mformat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Date())
                    NotificationTask(this, NotificationInfo(symbol, numbers, mformat)).execute()
                } else {
                    toast("exist value")
                }
            } catch (e: Exception) {
                toast("input type error")
            }

        }
    }


    fun setList(info: NotificationInfo) {
        Log.d("asdf", "${info.symbol}_${info.value}")
        myList.add(info)
        Collections.sort(myList, InformationComare())
        myAdapter?.update(myList)
        myView.texter.text.clear()
        val set = HashSet<String>()
        for (k in myList) {
            set.add("${k.symbol}:${k.value}:${k.date}")
        }
        Util.noticom = set
        Util.sharedPreferences_editor.putStringSet("noticom", set).commit()
        FirebaseMessaging.getInstance().subscribeToTopic("${info.symbol}_${info.value}")
    }

    fun toast(str: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }
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