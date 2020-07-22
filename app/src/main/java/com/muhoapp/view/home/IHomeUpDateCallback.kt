package com.muhoapp.view.home

import com.muhoapp.model.domin.home.UpdateData

interface IHomeUpDateCallback {
    /**
     * 加载最新更新数据
     */
    fun onLoadUpDateContent(data: List<UpdateData>)

    /**
     * 加载更多最新更新数据
     */
    fun onLoadMoreUpDateContent(data: List<UpdateData>)
}