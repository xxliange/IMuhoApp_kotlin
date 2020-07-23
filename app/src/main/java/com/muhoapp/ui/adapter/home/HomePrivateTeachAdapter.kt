package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.PrivateTeachData

class HomePrivateTeachAdapter : RecyclerView.Adapter<HomePrivateTeachAdapter.InnerHolder>() {
    private var mData = ArrayList<PrivateTeachData>()
    class InnerHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: PrivateTeachData) {
            itemView.findViewById<TextView>(R.id.item_home_column_title).text = itemData.title
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_home_column_thumb))
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePrivateTeachAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_column,parent,false)
        return InnerHolder(itemView)
    }
    override fun getItemCount(): Int {
        return if(mData.size>=6){
            6
        }else{
            return 0
        }
    }

    override fun onBindViewHolder(holder: HomePrivateTeachAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData)
    }

    fun addData(data: List<PrivateTeachData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun cleanData() {
        mData.clear()
    }
}