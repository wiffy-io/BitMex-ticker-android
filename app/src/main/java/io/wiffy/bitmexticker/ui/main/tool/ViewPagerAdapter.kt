package io.wiffy.bitmexticker.ui.main.tool

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.getNavi
import kotlinx.android.synthetic.main.noti_fragment.view.*
import java.lang.Exception


class ViewPagerAdapter(fm: FragmentManager, private var mNumOfTabs: Int, arr: ArrayList<String>) :
    FragmentStatePagerAdapter(fm) {

    private val fragList: ArrayList<TabFragment> = ArrayList()

    init {
        for (tempo in arr) {
            fragList.add(TabFragment(tempo))
        }
    }

    override fun getItem(position: Int): Fragment = fragList[position]


    override fun getCount(): Int = mNumOfTabs

    fun updateTheme() {
        for (x in fragList) {
            try {
                x.themeChange()
            } catch (e: Exception) {
            }
        }
    }
}

class TabFragment(val str: String) : Fragment() {
    private var myView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.noti_fragment, container, false)
        myView?.frag1?.setBackgroundResource(getNavi())
        myView?.frag1_text?.text = str
        myView?.frag1_text?.setTextColor(resources.getColor(R.color.WHITE))
        return myView
    }

    fun themeChange() {
        if (myView != null) {
            myView?.frag1?.setBackgroundResource(getNavi())
            myView?.frag1_text?.text = str
            myView?.frag1_text?.setTextColor(resources.getColor(R.color.WHITE))
        }
    }
}

