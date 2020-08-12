package com.muhoapp.presenter.presenterInterface

interface IPayAlbumPresenter {
    /**
     * 获取视频列表
     */
    fun getVideoList(sid : Int)

    /**
     * 获取视频信息
     */
    fun getVideoInfo(id : Int)

    /**
     * 获取更多视频数据
     */
    fun getVideoMoreData()

    /**
     * 获取视频收藏信息
     */
    fun getVideoCollect(cid : Int)
}