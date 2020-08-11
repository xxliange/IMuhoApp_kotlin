package com.muhoapp.view.video

import com.muhoapp.model.domin.video.HomeUpdateVideoData
import com.muhoapp.model.domin.video.HomeUpdateVideoMoreData

interface HomeUpdateVideoCallback {

    /**
     * 加载视频信息
     */
    fun onLoadVideoInfo(data : HomeUpdateVideoData)

    /**
     * 加载更多视频内容
     */
    fun onLoadMoreVideo(data : List<HomeUpdateVideoMoreData>)
}