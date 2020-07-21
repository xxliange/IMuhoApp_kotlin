package com.muhoapp.view.adapter.home

import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.muhoapp.model.domin.home.BannerData

class HomeLooperAdapter : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return false;
    }

    override fun getCount(): Int {
        return 0;
    }

    fun setData(data: BannerData?) {
    }
}