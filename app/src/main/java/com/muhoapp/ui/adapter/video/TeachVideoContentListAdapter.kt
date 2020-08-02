package com.muhoapp.ui.adapter.video

/**
 * 视频列表选集adapter
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhoapp.R
import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.view.utils.LogUtils

class TeachVideoContentListAdapter : RecyclerView.Adapter<TeachVideoContentListAdapter.InnerHolder>() {
    private var mData = ArrayList<TeachVideoListData>()
    private var onItemClickListener : OnItemClickListener? = null
    private var itemPosition : Int = 0
    class InnerHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: TeachVideoListData, position: Int) {
            itemView.findViewById<TextView>(R.id.item_teach_video_content_title).text = (position+1).toString()
            if (position>=3){
                itemView.findViewById<TextView>(R.id.item_teach_video_vip).visibility = View.VISIBLE
            }else{
                itemView.findViewById<TextView>(R.id.item_teach_video_vip).visibility = View.GONE
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeachVideoContentListAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_teach_video_content,parent,false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: TeachVideoContentListAdapter.InnerHolder, position: Int) {
       val itemData = mData[position]
        holder.bindData(itemData, position)
        holder.itemView.setOnClickListener{
            onItemClickListener?.onItemClick(itemData,position)
        }
        holder.itemView.findViewById<LinearLayout>(R.id.item_teach_video_content_title_container).isSelected = itemPosition == position
        if (itemPosition == position){
            holder.itemView.findViewById<TextView>(R.id.item_teach_video_content_title).setTextColor(holder.itemView.resources.getColor(R.color.mainColor,null))
        }else{
            holder.itemView.findViewById<TextView>(R.id.item_teach_video_content_title).setTextColor(holder.itemView.resources.getColor(R.color.colorBlack,null))
        }
    }

    fun addData(data: List<TeachVideoListData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun cleanData() {
        mData.clear()
    }

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun choseItem(pos: Int) {
        itemPosition = pos
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(data : TeachVideoListData,pos:Int)
    }

}