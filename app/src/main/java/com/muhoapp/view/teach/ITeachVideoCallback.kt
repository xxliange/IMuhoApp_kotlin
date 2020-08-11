package com.muhoapp.view.teach

import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.model.domin.video.TeachVideoMoreRandom
import com.muhoapp.model.domin.video.VideoCollectInfo

interface ITeachVideoCallback {
    /**
     * 获取视频列表
     */
    fun onLoadTeachVideoList(data : List<TeachVideoListData>)

    /**
     * 获取视频推荐
     */
    fun onLoadTeachMoreRandom(data : List<TeachVideoMoreRandom>)
    /**
     * 观看数加一
     */
    fun onLoadViewVideoSuccess()
    /**
     * 获取视频收藏数和用户收藏情况
     */
    fun onLoadVideoCollect(data : VideoCollectInfo)

    fun onLoadVideoUserCollect(msg : String)
}