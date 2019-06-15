package com.pale_cosmos.bitmexticker.ui.Main

import android.app.Dialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.MobileAds
import com.pale_cosmos.bitmexticker.extension.*
import com.pale_cosmos.bitmexticker.model.Coin_info
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.model.Util.Companion.info_on
import com.pale_cosmos.bitmexticker.model.Util.Companion.setting_on
import com.pale_cosmos.bitmexticker.ui.Information.InformationActivity
import com.pale_cosmos.bitmexticker.ui.Setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.lang.Exception
import com.google.android.gms.ads.AdRequest
import com.pale_cosmos.bitmexticker.R


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

        init_loading()
        start_loading()
        mPresenter = MainPresenter(this)
        mPresenter.change_UI()
        mPresenter.get_coin()

    }

    override fun onResume() {
        super.onResume()
        mPresenter.socker_reconnect()
    }

    override fun onDestroy() {
        super.onDestroy()
        //true_ = false
        //Log.d("asdf","des")
    }

    override fun set_recycler(init_coin: ArrayList<Coin_info>) {
        Handler(applicationContext.mainLooper).post {
            myAdapter = MainAdapter(init_coin, this, Util.dark_theme,this)
            recycler.adapter = myAdapter
            recycler.layoutManager = LinearLayoutManager(this)
        }
        mPresenter.make_socket()
    }

    override fun update_recycler_theme() {
        Handler(applicationContext.mainLooper).post {
            myAdapter?.update_theme(Util.dark_theme)
        }
    }

    override fun update_recycler(mod_coin: ArrayList<Coin_info>) {
        Handler(applicationContext.mainLooper).post {
            myAdapter?.update(mod_coin)
        }
    }

    override fun changeUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(get_navi())
        window.navigationBarColor = resources.getColor(darkAndLight_reverse())
        brightness_main.setImageResource(get_brightness())
        toolbar_main.background = resources.getDrawable(get_navi())
        parent_main.background = resources.getDrawable(get_table_out())
        bgc.setCardBackgroundColor(ContextCompat.getColor(applicationContext, get_table_in()))
        trd_info.setTextColor(ContextCompat.getColorStateList(applicationContext, darkAndLight()))
    }

    override fun change_recent(str:String){
        trd_info.text = str
    }

    override fun addBrightnessListener(listener: View.OnClickListener) {
        brightness_main.setOnClickListener(listener)
    }

    override fun addSettingActivityChangeListener(listener: View.OnClickListener) {
        setting_main.setOnClickListener(listener)
    }

    override fun moveToSetting() {
        if(setting_on) {
            setting_on = false
            startActivity(Intent(this@MainActivity, SettingActivity::class.java))
            overridePendingTransition(R.anim.rightin_activity, R.anim.leftout_activity)
        }
    }

    override fun moveToInformation(bundle: Bundle) {
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

    override fun start_loading(){
        if(!check_loading()){
            Handler(Looper.getMainLooper()).post {
                try { builder?.show() }catch (e:Exception){ }
            }
        }
    }

    fun init_loading(){
        builder = Dialog(this)
        builder?.setContentView(R.layout.waitting_dialog)
        builder?.setCancelable(false)
        builder?.setCanceledOnTouchOutside(false)
        builder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun stop_loading(){
        if (builder?.isShowing!!){
            builder?.dismiss()
        }
    }

    override fun check_loading():Boolean{
        return builder?.isShowing!!
    }
}