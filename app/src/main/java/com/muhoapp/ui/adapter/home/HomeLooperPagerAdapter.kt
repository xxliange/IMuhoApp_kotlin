package com.muhoapp.ui.adapter.home

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.viewpager.widget.PagerAdapter
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.muhoapp.R
import com.muhoapp.model.domin.home.BannerData
import com.muhoapp.view.utils.LogUtils

class HomeLooperPagerAdapter : PagerAdapter() {
    private var title : TextView? = null
    private var looperContainer : RelativeLayout? = null
    private var mData = ArrayList<BannerData>()
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        if (mData.size > 0) {
            return Int.MAX_VALUE
        } else {
            return 0
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val iv = ImageView(container.context)
        val layoutForm = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        iv.layoutParams = layoutForm
        iv.scaleType = ImageView.ScaleType.CENTER_CROP
        val itemData = mData[position % mData.size]
        Glide
            .with(container.context)
            .asBitmap()
            .load(itemData.pic)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(iv)
        container.addView(iv)
        return iv
    }

    fun addData(data: List<BannerData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    fun cleanData() {
        mData.clear()
    }
}