package com.muhoapp.ui.adapter.video

/**
 * 视频查看更多集数viewpager adapter
 */

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.ui.fragment.video.TeachVideoCountListFragment
import com.muhoapp.ui.fragment.video.TeachVideoCountListInfoFragment
import com.muhoapp.view.utils.LogUtils
import java.util.ArrayList

class TeachVideoContentCountViewPagerAdapter(fm:FragmentManager) :  FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private var mData = ArrayList<Int>()
    private var mListData = ArrayList<TeachVideoListData>()
    private var cid = 0
    override fun getItem(position: Int): Fragment {
        return if(position == 0){
            TeachVideoCountListFragment.newInstance(mListData,cid)
        }else{
            TeachVideoCountListInfoFragment.newInstance(mListData)
        }
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
         if(position == 0){
             return  "选集"
        }else if(position == 1){
            return "分集介绍"
        }
        return "选集"
    }

    fun addData(countTabList: ArrayList<Int>) {
        mData.clear()
        mData.addAll(countTabList)
        notifyDataSetChanged()
    }

    fun addListData(data: List<TeachVideoListData>) {
        mListData.clear()
        mListData.addAll(data)
        notifyDataSetChanged()
    }

    fun setCid(cid: Int) {
        this.cid = cid
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (position == 0) {
            val fragment = super.instantiateItem(container, position) as TeachVideoCountListFragment
            fragment.upDateArguments(cid)
            return fragment
        }
        return super.instantiateItem(container, position)
    }

}