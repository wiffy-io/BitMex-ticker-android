package com.pale_cosmos.bitmexticker.ui.Dialog

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.pale_cosmos.bitmexticker.R
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : AppCompatActivity(), DialogContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        titleInDialog.text = intent.getStringExtra("TITLE")
        contextInDialog.text = intent.getStringExtra("CONTEXT")
        OKBUTTON.setOnClickListener {
            moveToBack()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_OUTSIDE) {
         moveToBack()
        }

        return false
    }

    override fun moveToBack() {
        finish()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

    override fun onBackPressed() {
        moveToBack()
    }
}