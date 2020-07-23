package com.muhoapp.utils

import com.muhoapp.presenter.impl.home.HomePagerPresenterImpl
import com.muhoapp.presenter.impl.home.HomePresenterImpl
import com.muhoapp.presenter.impl.home.HomeSkillSortPresenterImpl
import com.muhoapp.presenter.impl.home.HomeUpDatePresenterImpl
import com.muhoapp.presenter.impl.teach.TeachPagerPresenterImpl

object PresenterManager {
    private val homePresenter: HomePresenterImpl by lazy {
        HomePresenterImpl()
    }

    fun getHomePresenterImpl(): HomePresenterImpl {
        return homePresenter
    }

    private val homePagerPresenter: HomePagerPresenterImpl by lazy {
        HomePagerPresenterImpl()
    }

    fun getHomePagerPresenterImpl(): HomePagerPresenterImpl {
        return homePagerPresenter
    }

    private val homeSkillSortPresenter: HomeSkillSortPresenterImpl by lazy {
        HomeSkillSortPresenterImpl()
    }

    fun getHomeSkillSortPresenterImpl(): HomeSkillSortPresenterImpl {
        return homeSkillSortPresenter
    }

    private val homeUpDatePresenter: HomeUpDatePresenterImpl by lazy {
        HomeUpDatePresenterImpl()
    }

    fun getHomeUpDatePresenterImpl(): HomeUpDatePresenterImpl {
        return homeUpDatePresenter
    }

    private val teachPagerPresenter: TeachPagerPresenterImpl by lazy {
        TeachPagerPresenterImpl()
    }

    fun getTeachPagerPresenterImpl(): TeachPagerPresenterImpl {
        return teachPagerPresenter
    }
}