package io.wiffy.bitmexticker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import io.wiffy.bitmexticker.R
import io.wiffy.bitmexticker.extension.getNavi
import kotlinx.android.synthetic.main.noti_fragment1.view.*
import kotlinx.android.synthetic.main.noti_fragment2.view.*
import kotlinx.android.synthetic.main.noti_fragment3.view.*
import java.lang.Exception


class ViewPagerAdapter(fm: FragmentManager, private var mNumOfTabs: Int,arr: ArrayList<String>) :
    FragmentStatePagerAdapter(fm) {

    private val frag1: TabFragment1 = TabFragment1(arr[0])
    private val frag2: TabFragment2 = TabFragment2(arr[1])
    private val frag3: TabFragment3 = TabFragment3(arr[2])

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> frag1
            1 -> frag2
            2 -> frag3
            else -> TabFragment1("ERROR")
        }
    }

    override fun getCount(): Int = mNumOfTabs

    fun updateTheme() {
        try {
        frag1.themeChange()
        frag2.themeChange()
        frag3.themeChange()
        }catch (e:Exception){}
    }
}

class TabFragment1(val str: String) : Fragment() {
    private var myView: View?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.noti_fragment1, container, false)
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

class TabFragment2(val str: String) : Fragment() {
    private var myView: View?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.noti_fragment2, container, false)
        myView?.frag2?.setBackgroundResource(getNavi())
        myView?.frag2_text?.text = str
        myView?.frag2_text?.setTextColor(resources.getColor(R.color.WHITE))
        return myView
    }

    fun themeChange() {
        if (myView != null) {
            myView?.frag2?.setBackgroundResource(getNavi())
            myView?.frag2_text?.text = str
            myView?.frag2_text?.setTextColor(resources.getColor(R.color.WHITE))
        }
    }
}


class TabFragment3(val str: String) : Fragment() {
    private var myView: View?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.noti_fragment3, container, false)
        myView?.frag3?.setBackgroundResource(getNavi())
        myView?.frag3_text?.text = str
        myView?.frag3_text?.setTextColor(resources.getColor(R.color.WHITE))
        return myView
    }

    fun themeChange() {
        if (myView != null) {
            myView?.frag3?.setBackgroundResource(getNavi())
            myView?.frag3_text?.text = str
            myView?.frag3_text?.setTextColor(resources.getColor(R.color.WHITE))
        }
    }
}