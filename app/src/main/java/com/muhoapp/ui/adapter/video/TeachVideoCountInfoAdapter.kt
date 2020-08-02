package com.muhoapp.ui.adapter.video

/**
 * 视频查看详情选集列表adapter
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhoapp.R
import com.muhoapp.model.domin.video.TeachVideoListData
import java.text.MessageFormat

class TeachVideoCountInfoAdapter : RecyclerView.Adapter<TeachVideoCountInfoAdapter.InnerHolder>() {
    private var mData = ArrayList<TeachVideoListData>()

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(
            itemData: TeachVideoListData,
            position: Int
        ) {
            itemView.findViewById<TextView>(R.id.item_teach_video_count_info_title).text = MessageFormat.format("第{0}集", position+1)
            itemView.findViewById<TextView>(R.id.item_teach_video_count_info).text = itemData.title
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeachVideoCountInfoAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teach_video_count_info, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: TeachVideoCountInfoAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData, position)
    }

    fun addData(data: List<TeachVideoListData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun cleanData() {
        mData.clear()
    }
}