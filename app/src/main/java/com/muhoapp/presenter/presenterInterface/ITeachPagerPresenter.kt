package com.muhoapp.presenter.presenterInterface

interface ITeachPagerPresenter {
    /**
     * 加载最新教学列表数据
     */
    fun getNewTeachListData(type: Int)
    /**
     * 加载更多最新教学列表数据
     */
    fun getMoreNewTeachListData(type: Int)
    /**
     * 加载教学列表数据
     */
    fun getSubjectTeachListData(type : Int)
    /**
     * 加载更多教学列表数据
     */
    fun getMoreSubjectTeachListData(type: Int)
}