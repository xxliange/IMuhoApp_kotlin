package com.muhoapp.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.video.TeachVideoMoreRandom
import java.text.MessageFormat

class TeachVideoMoreRandomAdapter : RecyclerView.Adapter<TeachVideoMoreRandomAdapter.InnerHolder>() {
    private var mData = ArrayList<TeachVideoMoreRandom>()
    class InnerHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: TeachVideoMoreRandom) {
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_home_skill_thumb))
            itemView.findViewById<TextView>(R.id.item_home_skill_title).text = itemData.name
            itemView.findViewById<TextView>(R.id.item_home_skill_count).text = MessageFormat.format("共{0}集",itemData.count)
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeachVideoMoreRandomAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_skill,parent,false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: TeachVideoMoreRandomAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<TeachVideoMoreRandom>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun cleanData() {
        mData.clear()
    }
}