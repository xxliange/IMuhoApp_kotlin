package com.muhoapp.ui.adapter.teach

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.muhoapp.ui.fragment.homeScrean.teach.TeachPagerFragment
import com.muhoapp.view.utils.LogUtils
import java.util.*

class TeachPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var mData = ArrayList<Int>()
    override fun getItem(position: Int): Fragment {
        LogUtils.d(this, "position --> $position getItem")
        val type = mData[position]
        return TeachPagerFragment.newInstance(type)
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (mData[position]) {
            0 -> {
                return "最新教学"
            }
            1 -> {
                return "季度教学"
            }
            4 -> {
                return "技术教学"
            }
            2 -> {
                return "研究所"
            }
        }
        return null
    }

    fun addData(tabList: ArrayList<Int>) {
        mData.clear()
        mData.addAll(tabList)
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return super.isViewFromObject(view, `object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }
}