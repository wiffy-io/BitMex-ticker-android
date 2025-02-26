package io.wiffy.bitmexticker.ui.information.notificationFragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.messaging.FirebaseMessaging
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.function.*
import io.wiffy.bitmexticker.model.data.CoinInfo
import io.wiffy.bitmexticker.model.Component
import io.wiffy.bitmexticker.model.Component.dark_theme
import io.wiffy.bitmexticker.model.VerticalSpaceItemDecoration
import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.InformationComparator
import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.NotificationAdapter
import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.NotificationInfo
import io.wiffy.bitmexticker.ui.information.notificationFragment.tool.NotificationTask
import kotlinx.android.synthetic.main.fragment_notification.view.*
import java.util.*
import kotlin.collections.ArrayList

class NotificationFragment : NotificationContract.View() {
    private lateinit var myView: View
    private lateinit var mPresenter: NotificationPresenter
    private lateinit var parentLayout: RelativeLayout
    private var ini = true
    var symbol: String? = null
    private var xbtPrice: String = "0"
    private var myAdapter: NotificationAdapter? = null
    private lateinit var myList: ArrayList<NotificationInfo>
    private var builder: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        myList = ArrayList<NotificationInfo>().apply {
            Component.notificationSet?.let {
                for (x in it.iterator()) {
                    val y = x.split(":")
                    this.add(NotificationInfo(y[0], y[1], y[2]))
                }
            }
            Collections.sort(this, InformationComparator)
        }
        myAdapter = NotificationAdapter(
            myList,
            this@NotificationFragment,
            symbol
        )

        myView.noticycle.adapter = myAdapter
        myView.noticycle.layoutManager = LinearLayoutManager(activity?.applicationContext!!)

        with(resources)
        {
            myView.notis.background = getDrawable(getTableOut())
            parentLayout = myView.findViewById(R.id.notis)
            parentLayout.background = getDrawable(getFragmentBackground())
            //myView.texter.setBackgroundColor(getColor(getEditTextColor()))
            myView.editcard.setCardBackgroundColor(getColor(getEditTextColor()))
            myView.texter.setTextColor(getColor(getTitle()))

            if (dark_theme) {
                myView.angimotti.background = getDrawable(R.drawable.chart_border_dark)
                myView.noti_view.background = getDrawable(R.drawable.chart_border_dark)
                myView.noti_context.background = getDrawable(R.drawable.chart_border_dark)
            } else {
                myView.noti_view.background = getDrawable(R.drawable.chart_border_light)
                myView.noti_context.background = getDrawable(R.drawable.chart_border_light)
                myView.angimotti.background = getDrawable(R.drawable.chart_border_light)
            }
            myView.noticycle.addItemDecoration(VerticalSpaceItemDecoration)
            myView.noticycle.setBackgroundColor(getColor(getTableOut()))
        }

        myView.cdcd123.setOnClickListener {
            if (Component.isConsumer) {
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
                        if (numbers.toDouble().toInt().toDouble() == numbers.toDouble())
                            numbers = numbers.split(".")[0]
                        NotificationTask(
                            this,
                            NotificationInfo(symbol, numbers, getTimeFormat("yyyy/MM/dd HH:mm:ss"))
                        ).execute()
                    } else {
                        toast("exist value")
                    }
                } catch (e: Exception) {
                    toast("input type error")
                }
            } else {
                toast("Pro Function")
            }
        }
    }

    override fun builderUp() {
        if (builder == null) {
            builder = Dialog(context!!).apply {
                setContentView(R.layout.dialog_indicator)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
        }
        if (builder?.isShowing == false) Handler(Looper.getMainLooper()).post { builder?.show() }
    }

    override fun builderDismiss() {
        if (builder?.isShowing == true) Handler(Looper.getMainLooper()).post { builder?.dismiss() }
    }

    override fun sendContext(): Context? = context

    fun setList(info: NotificationInfo) {
        FirebaseMessaging.getInstance().subscribeToTopic("${info.symbol}_${info.value}")
            .addOnCompleteListener {
                with(myList)
                {
                    add(info)
                    Collections.sort(this, InformationComparator)
                    myAdapter?.update(this)
                }

                setShared("notificationSet", HashSet<String>().apply {
                    for (k in myList) {
                        add("${k.symbol}:${k.value}:${k.date}")
                    }
                    Component.notificationSet = this
                })

                builderDismiss()
                if (!it.isSuccessful) toast("Error.")
            }
    }

    @SuppressLint("SetTextI18n")
    fun setXBT(str: String) {
        try {
            if (!symbol?.contains("USD")!! && xbtPrice != "0") {
                myView.noti_sub_price.text =
                    "≈ ${changeValue(str.toDouble() * xbtPrice.toDouble())} $"
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
            if (ini) {
                myView.texter.setText(str)
                ini = false
            }
        } catch (e: Exception) {
        }
    }
}