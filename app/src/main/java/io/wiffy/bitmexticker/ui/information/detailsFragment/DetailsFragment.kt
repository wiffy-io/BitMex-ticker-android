package io.wiffy.bitmexticker.ui.information.detailsFragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.getFragmentBackground
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsFragment : Fragment(), DetailsContract.View {
    lateinit var myView: View
    lateinit var mPresenter: DetailsPresenter
    lateinit var url: String
    lateinit var asyncTask: DetailsTask

    var builder: Dialog? = null
    var myAdapter: DetailsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.fragment_details, container, false)
        url = "https://www.bitmex.com/app/contract/${arguments?.getString("symbol")}"
        asyncTask = DetailsTask(this, url)

        mPresenter = DetailsPresenter(this)
        mPresenter.init()

        return myView
    }

    override fun changeUI() {
        myView.details.background = resources.getDrawable(getFragmentBackground())
        asyncTask.execute()
    }

    override fun onDestroy() {
        builder?.dismiss()
        super.onDestroy()
    }

    override fun asyncPre() {
        builder = Dialog(activity!!).apply {
            setContentView(R.layout.waitting_dialog)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
        Handler(Looper.getMainLooper()).post {
            builder?.show()
        }
    }

    override fun updateRecycler(arr: ArrayList<DetailsInfo>) {
        myAdapter = DetailsAdapter(arr, context!!)
        myView.detailsRecycler.adapter = myAdapter
        myView.detailsRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
    }

    override fun asyncPost() {
        builder?.dismiss()
    }

}