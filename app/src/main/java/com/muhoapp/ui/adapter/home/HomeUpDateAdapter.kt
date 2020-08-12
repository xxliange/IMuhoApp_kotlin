package com.muhoapp.ui.adapter.home

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.UpdateData
import com.muhoapp.ui.adapter.video.VideoTagViewAdapter
import com.muhoapp.view.utils.LogUtils
import java.text.MessageFormat

class HomeUpDateAdapter : RecyclerView.Adapter<HomeUpDateAdapter.InnerHolder>() {
    private var mData = ArrayList<UpdateData>()
    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: UpdateData)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var videoTagAdapter: VideoTagViewAdapter? = null
        fun bindData(itemData: UpdateData) {
            videoTagAdapter = VideoTagViewAdapter()
            val linearLayoutManager = LinearLayoutManager(itemView.context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            val tagView = itemView.findViewById<RecyclerView>(R.id.item_update_tag_view)
            tagView.layoutManager = linearLayoutManager
            tagView.adapter = videoTagAdapter
            videoTagAdapter?.addData(itemData.tags)
            Glide.with(itemView.context).load(itemData.thumb1).placeholder(R.drawable.placeholder)
                .into(itemView.findViewById(R.id.item_update_thumb))
            itemView.findViewById<TextView>(R.id.item_update_view).text =
                MessageFormat.format("{0}次观看", itemData.view)
            itemView.findViewById<TextView>(R.id.item_update_collect).text =
                MessageFormat.format("{0}人收藏", itemData.collection)
            itemView.findViewById<TextView>(R.id.item_update_time).text = itemData.update_time
            itemView.findViewById<TextView>(R.id.item_update_title).text = itemData.title
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeUpDateAdapter.InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_update, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: HomeUpDateAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(itemData)
        }
        holder.bindData(itemData)
    }

    fun addData(data: List<UpdateData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun addMoreData(data: List<UpdateData>) {
        val oldSize = mData.size
        mData.addAll(data)
        notifyItemRangeChanged(oldSize, data.size)
    }

    fun cleanData() {
        mData.clear()
    }
}