package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.SkillContentData

class HomeSkillContentAdapter : RecyclerView.Adapter<HomeSkillContentAdapter.InnerHolder>() {
    private var mData = ArrayList<SkillContentData>()
    class InnerHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: SkillContentData) {
            itemView.findViewById<TextView>(R.id.item_home_skill_title).text = itemData.title
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_home_skill_thumb))
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeSkillContentAdapter.InnerHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_skill,parent,false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if(mData.size >= 4){
            4
        }else{
            return mData.size
        }
    }

    override fun onBindViewHolder(holder: HomeSkillContentAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<SkillContentData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}