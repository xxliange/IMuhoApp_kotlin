package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.SkillContentData
import com.muhoapp.model.domin.home.SkillSortData
import com.muhoapp.ui.fragment.homeScrean.home.HomeSkillSortFragment
import com.muhoapp.view.utils.LogUtils

//class HomeSkillSortPagerAdapter(fm:FragmentManager) : FragmentStatePagerAdapter(fm,
//    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//    private var mData = ArrayList<SkillSortData>()
//    override fun getItem(position: Int): Fragment {
//        val kw = mData[position]
//        return HomeSkillSortFragment.newInstance(kw)
//    }
//
//    override fun getCount(): Int {
//        return mData.size
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return mData[position].keyword
//    }
//
//    fun addData(data: List<SkillSortData>) {
//        mData.clear()
//        mData.addAll(data)
//        notifyDataSetChanged()
//    }
//
//    fun cleanData() {
//        mData.clear()
//    }
//}

class HomeSkillSortPagerAdapter : RecyclerView.Adapter<HomeSkillSortPagerAdapter.InnerHolder>() {
    private var mData = ArrayList<SkillContentData>()

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(itemData: SkillContentData) {
            itemView.findViewById<TextView>(R.id.item_home_skill_title).text = itemData.title
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_home_skill_thumb))
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeSkillSortPagerAdapter.InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_skill, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (mData.size > 4) {
            4
        } else {
            mData.size
        }
    }

    override fun onBindViewHolder(holder: HomeSkillSortPagerAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<SkillContentData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun cleanData() {
        mData.clear()
    }

}