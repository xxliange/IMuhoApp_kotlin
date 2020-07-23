package com.muhoapp.ui.adapter.teach

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.teach.SubjectTeachData

class TeachPagerViewAdapter : RecyclerView.Adapter<TeachPagerViewAdapter.InnerHolder>() {
    private var mData = ArrayList<SubjectTeachData>()
    private var mType: Int? = null

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(itemData: SubjectTeachData, mType: Int?) {
            var itemTitle = ""
            itemTitle = if (mType == 0) {
                itemData.title
            } else {
                itemData.name
            }
            itemView.findViewById<TextView>(R.id.item_home_skill_title).text = itemTitle
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder)
                .into(itemView.findViewById(R.id.item_home_skill_thumb))
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeachPagerViewAdapter.InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_skill, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: TeachPagerViewAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData, mType)
    }

    fun addData(data: List<SubjectTeachData>, type: Int) {
        mData.clear()
        mData.addAll(data)
        mType = type
        notifyDataSetChanged()
    }

    fun addMoreData(data: List<SubjectTeachData>) {
        val oldSize = mData.size
        mData.addAll(data)
        notifyItemRangeChanged(oldSize - 1, data.size)
    }

    fun cleanData() {
        mData.clear()
    }
}