package com.pale_cosmos.bitmexticker.ui.Main

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        mPresenter = MainPresenter(this)
        mPresenter.change_UI()
        mPresenter.make_socket()

    }

    override fun changeStatusBar(color:Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getResources().getColor(color);
    }

    override fun append_text(str: String) {
        test.setText(str)
    }


}