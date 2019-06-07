package com.pale_cosmos.bitmexticker.ui.Dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity:AppCompatActivity(), DialogContract.View
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        titleInDialog.text = intent.getStringExtra("TITLE")
        contextInDialog.text = intent.getStringExtra("CONTEXT")
        OKBUTTON.setOnClickListener {
            finish()
        }

    }
}