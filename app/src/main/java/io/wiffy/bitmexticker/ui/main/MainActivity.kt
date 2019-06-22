package io.wiffy.bitmexticker.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var mPresenter: MainPresenter
    var myAdapter: MainAdapter? = null
    var myPagerAdapter: ViewPagerAdapter? = null
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
        init_loading()
        mPresenter = MainPresenter(this, applicationContext)
        mPresenter.changeUI()
        mPresenter.getCoin(intent.getStringExtra("symbol"))

        //test
        val strings = ArrayList<String>()
        strings.add("안녕 난 최고")
        strings.add("안녕 너도 최고")
        strings.add("안녕 우리는 최고")
        myPagerAdapter = ViewPagerAdapter(supportFragmentManager,3,strings)
        viewPager.adapter = myPagerAdapter
        val newHandler = Handler()
        var currentPage = 0
        val update = Runnable {
            if (currentPage == strings.size) currentPage = 0
            Handler(Looper.getMainLooper()).post {
                viewPager.setCurrentItem(currentPage, true)
                currentPage += 1
            }
        }

        val mTimer = Timer()
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                newHandler.post(update)
            }
        }, 550, 3300) //3.3초마다 550의 속도로 바뀜
        tabLayout.setupWithViewPager(viewPager,true)
        //test
    }

    private fun agreement() {
        if (!Util.sharedPreferences.getBoolean("agreement", false)) {
            val builder = AlertDialog.Builder(this, getDialog())
            builder.setTitle(R.string.Agreement)
            builder.setMessage(R.string.agreementContext)
            builder.setPositiveButton("OK") { _, _ ->
                Util.sharedPreferences_editor.putBoolean("agreement", true).commit()
            }
            builder.setNegativeButton("Cancel") { _, _ ->
                finish()
            }
            builder.setCancelable(false)
            builder.show()
        }
    }

    override fun onResume() {
        super.onResume()
        changeUI()
        updateRecyclerTheme()
        myPagerAdapter?.updateTheme()
        mPresenter.socketReconnect()
    }

    override fun setRecycler(init_coin: ArrayList<CoinInfo>) {
        Handler(applicationContext.mainLooper).post {
            myAdapter = MainAdapter(init_coin, this, Util.dark_theme, this)
            recycler.adapter = myAdapter
            recycler.layoutManager = LinearLayoutManager(this)
            //recycler.addItemDecoration(DividerItemDecoration(applicationContext, 1));
            //var dividerItemDecoration = DividerItemDecoration(getApplicationContext(),LinearLayoutManager(this).getOrientation());
            //recycler.addItemDecoration(dividerItemDecoration);
            recycler.addItemDecoration(VerticalSpaceItemDecoration(2));
        }
        mPresenter.makeSocket()
    }

    override fun updateRecyclerTheme() {
        Handler(applicationContext.mainLooper).post {
            myAdapter?.updateTheme(Util.dark_theme)
        }
    }

    override fun updateRecycler(mod_coin: ArrayList<CoinInfo>) {
        Handler(applicationContext.mainLooper).post {
            myAdapter?.update(mod_coin)
        }
    }

    override fun changeUI() {
        tabLayout.setBackgroundResource(getNavi())
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


    override fun addSettingActivityChangeListener(listener: View.OnClickListener) {
        setting_main.setOnClickListener(listener)
    }

    override fun moveToSetting() {
        if (setting_on) {
            setting_on = false
            startActivity(Intent(this@MainActivity, SettingActivity::class.java))
            overridePendingTransition(R.anim.rightin_activity, R.anim.leftout_activity)
        }
    }

    override fun moveToInformation(bundle: Bundle) {
        mPresenter.setSymbol(bundle.getString("information",""))
        var intents = Intent(this@MainActivity, InformationActivity::class.java)
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

    fun init_loading() {
        builder = Dialog(this)
        builder?.setContentView(R.layout.waitting_dialog)
        builder?.setCancelable(false)
        builder?.setCanceledOnTouchOutside(false)
        builder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        startLoading()
    }

    override fun stopLoading() {
        if (builder?.isShowing!!) {
            builder?.dismiss()
        }
    }

    override fun checkLoading(): Boolean {
        return builder?.isShowing!!
    }

    override fun attachBaseContext(newBase: Context?) {

        super.attachBaseContext(
            Util.wrap(
                newBase,
                Util.global
            )
        )

    }

    override fun tossSymbol(symbol: String) {
        (infoContext as InformationActivity).setPrice(symbol)
    }

    override fun tossXBT(xbt: String) {
        (infoContext as InformationActivity).setXBT(xbt)
    }
}