package com.muhoapp.view.teach

import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.model.domin.video.TeachVideoMoreRandom

interface ITeachVideoCallback {
    /**
     * 获取视频列表
     */
    fun onLoadTeachVideoList(data : List<TeachVideoListData>)

    /**
     * 获取视频推荐
     */
    fun onLoadTeachMoreRandom(data : List<TeachVideoMoreRandom>)
}