package com.muhoapp.ui.adapter.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.muhoapp.model.domin.home.SkillSortData
import com.muhoapp.ui.fragment.homeScrean.home.HomeSkillSortFragment

class HomeSkillSortPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var mData = ArrayList<SkillSortData>()
    override fun getItem(position: Int): Fragment {
        val kw = mData[position]
        return HomeSkillSortFragment.newInstance(kw)
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mData[position].keyword
    }

    fun addData(data: List<SkillSortData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}