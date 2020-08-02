package com.muhoapp.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhoapp.R
import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.view.utils.LogUtils

class TeachVideoContentCountAdapter :
    RecyclerView.Adapter<TeachVideoContentCountAdapter.InnerHolder>() {
    private var mData = ArrayList<TeachVideoListData>()
    private var mCid = 0

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(itemData: TeachVideoListData, position: Int) {
            itemView.findViewById<TextView>(R.id.item_teach_video_content_title).text =
                (position + 1).toString()
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
    ): TeachVideoContentCountAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teach_video_content, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(
        holder: TeachVideoContentCountAdapter.InnerHolder,
        position: Int
    ) {
        val itemData = mData[position]
        holder.bindView(itemData, position)
        holder.itemView.findViewById<LinearLayout>(R.id.item_teach_video_content_title_container).isSelected = mCid == mData[position].cid
        if (mCid == mData[position].cid){
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

    fun setCid(cid: Int?) {
        mCid = cid!!
        notifyDataSetChanged()
    }
}