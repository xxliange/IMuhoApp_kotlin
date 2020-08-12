package com.muhoapp.view.video

import com.muhoapp.model.domin.video.PayAlbumInfoData
import com.muhoapp.model.domin.video.PayAlbumInfoMoreData
import com.muhoapp.model.domin.video.PayAlbumVideoCollectData
import com.muhoapp.model.domin.video.PayAlbumVideoData

interface PayAlbumVideoCallback {
    /**
     * 获取视频列表数据
     */
    fun onLoadVideoList(data: List<PayAlbumVideoData>)

    /**
     * 获取视频信息数据
     */
    fun onLoadVideoInfo(data: PayAlbumInfoData)

    /**
     * 获取更多视频列表数据
     */
    fun onLoadMoreVideo(data: List<PayAlbumInfoMoreData>)

    /**
     * 获取视频收藏信息
     */
    fun onLoadCollectInfo(data: PayAlbumVideoCollectData)
}