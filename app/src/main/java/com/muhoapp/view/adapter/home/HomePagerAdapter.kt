package com.muhoapp.view.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.muhoapp.R
import com.muhoapp.ui.fragment.homeScrean.home.HomePagerFragment
import com.muhoapp.ui.fragment.homeScrean.home.HomeUpdateFragment
import java.util.ArrayList

class HomePagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    lateinit var title : TextView

    private var mData: ArrayList<Int> = ArrayList();

    override fun getItem(position: Int): Fragment {
        if(position == 0){
            return HomePagerFragment.newInstance()
        }else{
            return HomeUpdateFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (mData.get(position) == 0) {
            return "推荐"
        }else{
            return "每日更新"
        }
    }

    fun addData(tabList: ArrayList<Int>?) {
        if (tabList !== null) {
            mData.clear()
            mData.addAll(tabList)
            notifyDataSetChanged()
        }
    }

    fun getTabView(i: Int, context: Context?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_home_tab, null)
        title = view.findViewById(R.id.item_home_tab_title)
        if (i == 0) {
            title.text = "推荐"
        }else{
            title.text="最新更新"
        }
        return view;

    }

}