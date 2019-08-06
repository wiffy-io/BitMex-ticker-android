package io.wiffy.bitmexticker.ui.main.tool

import android.annotation.SuppressLint
import android.os.AsyncTask
import io.wiffy.bitmexticker.ui.main.MainContract
import io.wiffy.bitmexticker.ui.main.MainPresenter
import java.lang.Exception
import kotlin.collections.ArrayList

@SuppressLint("StaticFieldLeak")
class InformationTask(private val mPresenter:MainPresenter, private val mView:MainContract.View) : AsyncTask<Void, Void, ArrayList<String>>() {

    override fun doInBackground(vararg params: Void?): ArrayList<String> =
//        try {
            mPresenter.parseInformation()
//        } catch (e: Exception) {
//            temp()
//        }

    override fun onPostExecute(result: ArrayList<String>?) {
        mView.setInformation(result)
    }

    private fun temp() = ArrayList<String>().apply {
        add("Error")
    }
}