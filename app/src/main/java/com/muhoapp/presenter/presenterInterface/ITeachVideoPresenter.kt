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
}