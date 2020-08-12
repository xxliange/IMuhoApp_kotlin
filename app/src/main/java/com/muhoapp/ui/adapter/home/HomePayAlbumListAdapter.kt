package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.PayAlbumData

class HomePayAlbumListAdapter : RecyclerView.Adapter<HomePayAlbumListAdapter.InnerHolder>() {
    private var mData = ArrayList<PayAlbumData>()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(data: PayAlbumData)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindNormalContent(itemData: PayAlbumData) {
            itemView.findViewById<TextView>(R.id.item_normal_block_title).text = itemData.name
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder)
                .into(itemView.findViewById(R.id.item_normal_block_img))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePayAlbumListAdapter.InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_normal_block, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (mData.size >= 4) {
            4
        } else {
            mData.size
        }
    }

    override fun onBindViewHolder(holder: HomePayAlbumListAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindNormalContent(itemData)
        holder.itemView.setOnClickListener{
            onItemClickListener?.onItemClick(itemData)
        }
    }

    fun addData(data: List<PayAlbumData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun cleanData() {
        mData.clear()
    }
}