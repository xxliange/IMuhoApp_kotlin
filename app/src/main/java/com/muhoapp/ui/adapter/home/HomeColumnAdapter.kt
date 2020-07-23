package com.muhoapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhoapp.R
import com.muhoapp.model.domin.home.PayAlbumData
import java.text.MessageFormat

class HomeColumnAdapter : RecyclerView.Adapter<HomeColumnAdapter.InnerHolder>() {
    private var mData = ArrayList<PayAlbumData>()
    class InnerHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bindData(itemData: PayAlbumData, position: Int) {
            itemView.findViewById<TextView>(R.id.item_home_column_title).text = itemData.name
            Glide.with(itemView.context).load(itemData.thumb).placeholder(R.drawable.placeholder).into(itemView.findViewById(R.id.item_home_column_thumb))
            if (position>=2){
                itemView.findViewById<TextView>(R.id.item_home_column_vip).visibility = View.VISIBLE
            }
            if (position>2){
                itemView.findViewById<TextView>(R.id.item_home_column_count).text = "已完结"
            }else {
                itemView.findViewById<TextView>(R.id.item_home_column_count).text = MessageFormat.format("更新至第{0}集",position)
            }

//            views.setText(MessageFormat.format("播放量 : {0}", data.get(0).getView()));

        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeColumnAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_column,parent,false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
       return if (mData.size>=4){
           4
       }else{
           return 0
       }
    }

    override fun onBindViewHolder(holder: HomeColumnAdapter.InnerHolder, position: Int) {
        val itemData = mData[position]
        holder.bindData(itemData,position)
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