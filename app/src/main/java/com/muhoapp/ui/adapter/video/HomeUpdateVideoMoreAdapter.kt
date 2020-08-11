package com.muhoapp.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.video.HomeUpdateVideoMoreData

class HomeUpdateVideoMoreAdapter : RecyclerView.Adapter<HomeUpdateVideoMoreAdapter.InnerHolder>() {
    private var mData = ArrayList<HomeUpdateVideoMoreData>()
    class InnerHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: HomeUpdateVideoMoreData) {
            Glide.with(itemView.context).load(itemData.thumb1).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_home_skill_thumb))
            itemView.findViewById<TextView>(R.id.item_home_skill_title).text = itemData.title
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeUpdateVideoMoreAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_skill,parent,false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: HomeUpdateVideoMoreAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<HomeUpdateVideoMoreData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
    }
}