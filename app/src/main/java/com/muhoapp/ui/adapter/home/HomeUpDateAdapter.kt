package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.UpdateData
import java.text.MessageFormat

class HomeUpDateAdapter : RecyclerView.Adapter<HomeUpDateAdapter.InnerHolder>() {
    private var mData = ArrayList<UpdateData>()

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(itemData: UpdateData) {
            Glide.with(itemView.context).load(itemData.thumb1).placeholder(R.drawable.placeholder)
                .into(itemView.findViewById(R.id.item_update_thumb))
            itemView.findViewById<TextView>(R.id.item_update_view).text = MessageFormat.format("{0}次观看",itemData.view)
            itemView.findViewById<TextView>(R.id.item_update_collect).text = MessageFormat.format("{0}人收藏",itemData.collection)
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
        notifyItemRangeChanged(oldSize,data.size)
    }

    fun cleanData() {
        mData.clear()
    }
}