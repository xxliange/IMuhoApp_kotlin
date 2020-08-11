package com.muhoapp.presenter.presenterInterface

interface ITeachVideoPresenter {
    /**
     * 加载视频列表数据
     */
    fun getTeachVideoListData()

    /**
     * 加载更多推荐
     */
    fun getTeachVideoMoreRandom()

    /**
     * 用户观看视频数加一
     */
    fun getViewVideo()

    /**
     * 获取视频收藏数和用户收藏视频状态
     */
    fun getVideoCollect(cid: Int)

    /**
     * 用户点击收藏取消收藏
     */
    fun getVideoUserCollect(cid : Int, type : Int, c_type:Int)
}