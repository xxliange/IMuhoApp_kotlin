package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.MineUserHistory

class MineUserHistoryAdapter : RecyclerView.Adapter<MineUserHistoryAdapter.InnerHolder>() {
    private var mData = ArrayList<MineUserHistory>()
    class InnerHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: MineUserHistory) {
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_mine_block_img))
            itemView.findViewById<TextView>(R.id.item_mine_block_title).text = itemData.title
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MineUserHistoryAdapter.InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_mine_block, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MineUserHistoryAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<MineUserHistory>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}