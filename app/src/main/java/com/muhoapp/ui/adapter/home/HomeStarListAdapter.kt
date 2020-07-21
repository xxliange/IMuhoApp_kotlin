package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.StarData

class HomeStarListAdapter : RecyclerView.Adapter<HomeStarListAdapter.InnerHolder>() {

    private var mData = ArrayList<StarData>()

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindNormalContent(itemData: StarData) {
            itemView.findViewById<TextView>(R.id.item_home_star_name).text = itemData.name
            Glide.with(itemView.context).load(itemData.avatar).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_home_star_avatar))
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeStarListAdapter.InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_star, parent, false)
        return InnerHolder(itemView)

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: HomeStarListAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindNormalContent(itemData)
    }

    fun addData(data: List<StarData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}