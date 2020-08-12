package com.muhoapp.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.video.PayAlbumInfoMoreData

class PayAlbumMoreAdapter : RecyclerView.Adapter<PayAlbumMoreAdapter.InnerHolder>() {
    private var mData = ArrayList<PayAlbumInfoMoreData>()
    class InnerHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: PayAlbumInfoMoreData) {
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_home_skill_thumb))
            itemView.findViewById<TextView>(R.id.item_home_skill_title).text = itemData.name
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PayAlbumMoreAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_skill,parent,false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: PayAlbumMoreAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<PayAlbumInfoMoreData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}