package com.muhoapp.view.home

import com.muhoapp.model.domin.home.*

interface IHomePagerCallback {
    /**
     * 获取首页banner图数据
     */
    fun onLooperDataLoad(data: List<BannerData>)
    /**
     * 获取球星列表数据
     */
    fun onStarDataLoad(data : List<StarData>)
    /**
     * 获取精品专辑列表数据
     */
    fun onPayAlbumDataLoad(data : List<PayAlbumData>)
    /**
     * 获取技术分类标签
     */
    fun onSkillSortDataLoad(data : List<SkillSortData>)
}