package com.muhoapp.view.home

import com.muhoapp.model.domin.home.SkillContentData

interface IHomeSkillSortCallback {
    /**
     * 获取技术列表内容
     */
    fun onSkillSortContentDataLoad(data : List<SkillContentData>,kw:String)
}