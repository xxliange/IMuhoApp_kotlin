package com.muhoapp.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhoapp.R

class VideoTagViewAdapter : RecyclerView.Adapter<VideoTagViewAdapter.InnerHolder>() {
    private var mData =  ArrayList<String>()
    class InnerHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: String) {
            itemView.findViewById<TextView>(R.id.item_video_tag_name).text = itemData
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoTagViewAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_video_tag,parent,false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: VideoTagViewAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<String>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
    }
}