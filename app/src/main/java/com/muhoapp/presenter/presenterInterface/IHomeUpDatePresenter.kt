package com.muhoapp.presenter.presenterInterface

interface IHomeUpDatePresenter {
    /**
     * 加载最新更新数据
     */
    fun getUpDateContent()
    /**
     * 加载更多最新更新数据
     */
    fun getMoreUpDateContent()
}