package com.muhoapp.ui.adapter.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.muhoapp.R
import com.muhoapp.model.domin.home.StarData
import jp.wasabeef.glide.transformations.CropCircleTransformation

class HomeStarListAdapter : RecyclerView.Adapter<HomeStarListAdapter.InnerHolder>() {

    private var mData = ArrayList<StarData>()

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindNormalContent(itemData: StarData) {
            itemView.findViewById<TextView>(R.id.item_home_star_name).text = itemData.name
//            itemView.findViewById<LinearLayout>(R.id.item_home_star_view).setBackgroundResource(R.drawable.item_home_star_avatar_back)
//            val background : GradientDrawable = itemView.findViewById<LinearLayout>(R.id.item_home_star_view).background as GradientDrawable
//            background.setColor(Color.parseColor(itemData.theme))
            Glide.with(itemView.context)
                .load(itemData.avatar)
//                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .placeholder(R.drawable.placeholder)
                .into(itemView.findViewById(R.id.item_home_star_avatar))
//            Glide.with(itemView.context)
//                .load(itemData.symbolpic)
//                .placeholder(R.drawable.placeholder)
//                .into(itemView.findViewById(R.id.item_home_star_sy))
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

    fun cleanData() {
        mData.clear()
    }
}