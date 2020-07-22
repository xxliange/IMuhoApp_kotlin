package com.muhoapp.presenter.presenterInterface

interface IHomePagerPresenter {
    /**
     * 获取banner图数据
     */
    fun getLooperData()
    /**
     * 获取球星数据
     */
    fun getStarData()
    /**
     * 获取精品专辑列表数据
     */
    fun getPayAlbumData()
    /**
     * 获取技术分类标签
     */
    fun getSkillSort()
    /**
     * 获取私人训练数据
     */
    fun getPrivateTeach()
}