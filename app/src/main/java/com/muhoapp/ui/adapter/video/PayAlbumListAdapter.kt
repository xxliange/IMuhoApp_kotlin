package com.muhoapp.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhoapp.R
import com.muhoapp.model.domin.video.PayAlbumVideoData

class PayAlbumListAdapter : RecyclerView.Adapter<PayAlbumListAdapter.InnerHolder>() {
    private var mData = ArrayList<PayAlbumVideoData>()
    private var mIndex = 0
    private var onItemClickListener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun itemClick(data: PayAlbumVideoData, index: Int)
    }
    fun setOnItemClickListener(listener : OnItemClickListener){
        this.onItemClickListener = listener
    }

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(itemData: PayAlbumVideoData, position: Int) {
            itemView.findViewById<TextView>(R.id.item_teach_video_content_title).text =
                (position + 1).toString()
            if (itemData.isfree == 0){
                itemView.findViewById<TextView>(R.id.item_teach_video_vip).visibility = View.VISIBLE
            }else{
                itemView.findViewById<TextView>(R.id.item_teach_video_vip).visibility = View.GONE
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PayAlbumListAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teach_video_content, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: PayAlbumListAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData, position)
        holder.itemView.findViewById<LinearLayout>(R.id.item_teach_video_content_title_container).isSelected =
            mIndex == position
        if (mIndex == position) {
            holder.itemView.findViewById<TextView>(R.id.item_teach_video_content_title)
                .setTextColor(holder.itemView.resources.getColor(R.color.mainColor, null))
        } else {
            holder.itemView.findViewById<TextView>(R.id.item_teach_video_content_title)
                .setTextColor(holder.itemView.resources.getColor(R.color.colorBlack, null))
        }
        holder.itemView.setOnClickListener{
            onItemClickListener?.itemClick(itemData,position)
        }
    }

    fun addData(data: List<PayAlbumVideoData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun getIndex(index: Int) {
        mIndex = index
        notifyDataSetChanged()
    }
}