package com.pale_cosmos.bitmexticker.ui.Main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_brightness
import com.pale_cosmos.bitmexticker.extension.get_navi
import com.pale_cosmos.bitmexticker.extension.get_table_out
import com.pale_cosmos.bitmexticker.model.Util
import com.pale_cosmos.bitmexticker.ui.Setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var mPresenter: MainPresenter
    var myAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        mPresenter = MainPresenter(this)
        mPresenter.change_UI()
        mPresenter.get_coin()

    }

    override fun set_recycler(init_coin: ArrayList<ConcurrentHashMap<String, String>>) {
        Handler(applicationContext.mainLooper).post {
            myAdapter = MainAdapter(init_coin, this, Util.dark_theme)
            recycler.adapter = myAdapter
            recycler.layoutManager = LinearLayoutManager(this)
            //myAdapter.notifyDataSetChanged()
        }
        mPresenter.make_socket()
    }

    override fun update_recycler_theme() {
        Handler(applicationContext.mainLooper).post {
            myAdapter?.update_theme(Util.dark_theme)
        }
    }

    override fun update_recycler(mod_coin: ArrayList<ConcurrentHashMap<String, String>>) {
        Handler(applicationContext.mainLooper).post {
            myAdapter?.update(mod_coin)
        }
    }

    override fun changeUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(get_navi())
        brightness_main.setImageResource(get_brightness())
        toolbar_main.background = resources.getDrawable(get_navi())
        parent_main.background = resources.getDrawable(get_table_out())
    }

    override fun addBrightnessListener(listener: View.OnClickListener) {
        brightness_main.setOnClickListener(listener)
    }

    override fun addSettingActivityChangeListener(listener: View.OnClickListener) {
        setting_main.setOnClickListener(listener)
    }

    override fun moveToSetting() {
        startActivity(Intent(this@MainActivity, SettingActivity::class.java))
        overridePendingTransition(R.anim.rightin_activity, R.anim.leftout_activity)
    }
}