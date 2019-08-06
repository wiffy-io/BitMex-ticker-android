package io.wiffy.bitmexticker.ui.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.*
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.MobileAds
import io.wiffy.bitmexticker.model.CoinInfo
import io.wiffy.bitmexticker.model.Util
import io.wiffy.bitmexticker.model.Util.Companion.setting_on
import io.wiffy.bitmexticker.ui.information.InformationActivity
import io.wiffy.bitmexticker.ui.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.lang.Exception
import com.google.android.gms.ads.AdRequest
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.*
import io.wiffy.bitmexticker.model.Util.Companion.infoContext
import io.wiffy.bitmexticker.model.VerticalSpaceItemDecoration
import io.wiffy.bitmexticker.ui.main.tool.InformationTask
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var mPresenter: MainPresenter
    var myAdapter: MainAdapter? = null
    var builder: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        MobileAds.initialize(this, "ca-app-pub-0355430122346055~1344719802")
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        agreement()
        initLoading()
        mPresenter = MainPresenter(this, applicationContext)
        mPresenter.changeUI()
        mPresenter.getCoin(intent.getStringExtra("symbol"))

    }

    private fun agreement() {
        if (!Util.sharedPreferences.getBoolean("agreement", false)) {
            AlertDialog.Builder(this, getDialog()).apply {
                setTitle(R.string.Agreement)
                setMessage(R.string.agreementContext)
                setPositiveButton("OK") { _, _ ->
                    Util.sharedPreferences_editor.putBoolean("agreement", true).commit()
                }
                setNegativeButton("Cancel") { _, _ ->
                    finish()
                }
                setCancelable(false)
            }.show()
        }
    }

    override fun onResume() {
        super.onResume()
        changeUI()
        updateRecyclerTheme()
        mPresenter.socketReconnect()
    }

    override fun setRecycler(init_coin: ArrayList<CoinInfo>) {
        Handler(applicationContext.mainLooper).post {
            myAdapter = MainAdapter(init_coin, this, Util.dark_theme, this)
            with(recycler)
            {
                adapter = myAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                addItemDecoration(VerticalSpaceItemDecoration(2))
            }
        }
        mPresenter.makeSocket()
    }

    override fun updateRecyclerTheme() = Handler(applicationContext.mainLooper).post {
        myAdapter?.updateTheme(Util.dark_theme)
    }


    override fun updateRecycler(mod_coin: ArrayList<CoinInfo>) = Handler(applicationContext.mainLooper).post {
        myAdapter?.update(mod_coin)
    }


    override fun changeUI() {
        myPagerLayout.background = resources.getDrawable(getTableIn())
        cap.setTextColor(
            ContextCompat.getColorStateList(
                applicationContext,
                io.wiffy.bitmexticker.extension.getTitle()
            )
        )
        dom.setTextColor(
            ContextCompat.getColorStateList(
                applicationContext,
                io.wiffy.bitmexticker.extension.getTitle()
            )
        )
        cap2.setTextColor(
            ContextCompat.getColorStateList(
                applicationContext,
                getTitle2()
            )
        )
        dom2.setTextColor(
            ContextCompat.getColorStateList(
                applicationContext,
                getTitle2()
            )
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(getNavi())
        window.navigationBarColor = resources.getColor(darkAndLightReverse())
        toolbar_main.background = resources.getDrawable(getNavi())
        parent_main.background = resources.getDrawable(getTableOut())
        bgc.setCardBackgroundColor(ContextCompat.getColor(applicationContext, getTableIn()))
        trd_info.setTextColor(ContextCompat.getColorStateList(applicationContext, darkAndLight()))
        BTC_r.background = resources.getDrawable(getTableIn())
        Symbol_.setTextColor(resources.getColor(getTitle2()))
        price_.setTextColor(resources.getColor(getTitle2()))
    }

    override fun changeRecent(str: String) {
        trd_info.text = str
    }


    override fun addSettingActivityChangeListener(listener: View.OnClickListener) =
        setting_main.setOnClickListener(listener)


    override fun moveToSetting() {
        if (setting_on) {
            setting_on = false
            startActivity(Intent(this@MainActivity, SettingActivity::class.java))
            overridePendingTransition(R.anim.rightin_activity, R.anim.leftout_activity)
        }
    }

    override fun moveToInformation(bundle: Bundle) {
        mPresenter.setSymbol(bundle.getString("information", ""))
        val intents = Intent(this@MainActivity, InformationActivity::class.java)
        intents.putExtras(bundle)
        startActivity(intents)
        overridePendingTransition(R.anim.rightin_activity, R.anim.leftout_activity)
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    override fun startLoading() {
        if (!checkLoading()) {
            Handler(Looper.getMainLooper()).post {
                try {
                    builder?.show()
                } catch (e: Exception) {
                }
            }
        }
    }

    private fun initLoading() {
        builder = Dialog(this).apply {
            setContentView(R.layout.waitting_dialog)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
        startLoading()
    }

    override fun initInformation() {
        InformationTask(mPresenter, this@MainActivity).execute()
    }

    @SuppressLint("SetTextI18n")
    override fun setInformation(list: ArrayList<String>?) {
        cap.text = "${resources.getString(R.string.market)} :"
        dom.text = "${resources.getString(R.string.dominance)} :"
        if (list?.size == 2) {
            cap2.text = "\$${list[0]}"
            dom2.text = list[1]
        } else {
            cap2.text = "No Data"
            dom2.text = "No Data"
        }
    }

    override fun stopLoading() {
        if (builder?.isShowing!!) {
            builder?.dismiss()
        }
    }

    override fun checkLoading() = builder?.isShowing ?: false

    override fun attachBaseContext(newBase: Context?) = super.attachBaseContext(
        Util.wrap(
            newBase,
            Util.global
        )
    )


    override fun tossSymbol(symbol: String) = (infoContext as InformationActivity).setPrice(symbol)


    override fun tossXBT(xbt: String) = (infoContext as InformationActivity).setXBT(xbt)

}