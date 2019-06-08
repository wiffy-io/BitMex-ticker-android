package com.pale_cosmos.bitmexticker.ui.Information

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.extension.get_navi
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity(), InformationContract.View {
    lateinit var mPresenter: InformationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        supportActionBar?.hide()
        mPresenter = InformationPresenter(this)
        mPresenter.init()

    }

    override fun changeUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(get_navi())
        //add this
        toMainFromInformation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_chevron_left_24, 0, 0, 0)
    }
}