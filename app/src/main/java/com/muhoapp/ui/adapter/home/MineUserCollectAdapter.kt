package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.MineUserCollect

class MineUserCollectAdapter : RecyclerView.Adapter<MineUserCollectAdapter.InnerHolder>() {
    private var mData = ArrayList<MineUserCollect>()
    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(itemData: MineUserCollect) {
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_mine_block_img))
            itemView.findViewById<TextView>(R.id.item_mine_block_title).text = itemData.title
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MineUserCollectAdapter.InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_mine_block, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MineUserCollectAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<MineUserCollect>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}