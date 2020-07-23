package com.muhoapp.view.teach

import com.muhoapp.model.domin.teach.NewTeachData
import com.muhoapp.model.domin.teach.SubjectTeachData

interface ITeachPagerCallback {
    /**
     * 加载最新教学列表数据
     */
    fun onNewTeachListDataLoad(data: List<SubjectTeachData>, type: Int)

    /**
     * 加载更多最新教学列表数据
     */
    fun onNewTeachListDataLoadMore(data: List<SubjectTeachData>, type: Int)

    /**
     * 加载教学列表数据
     */
    fun onSubjectTeachDataLoad(data: List<SubjectTeachData>, type: Int)

    /**
     * 加载更多教学列表数据
     */
    fun onSubjectTeachDataLoadMore(data: List<SubjectTeachData>, type: Int)
    /**
     *
     */
    fun onLoadDataEmpty()
}