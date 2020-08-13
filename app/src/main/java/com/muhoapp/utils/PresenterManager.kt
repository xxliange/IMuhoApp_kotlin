package com.muhoapp.utils

import com.muhoapp.presenter.impl.home.HomePagerPresenterImpl
import com.muhoapp.presenter.impl.home.HomePresenterImpl
import com.muhoapp.presenter.impl.home.HomeSkillSortPresenterImpl
import com.muhoapp.presenter.impl.home.HomeUpDatePresenterImpl
import com.muhoapp.presenter.impl.mine.MinePresenterImpl
import com.muhoapp.presenter.impl.teach.TeachPagerPresenterImpl
import com.muhoapp.presenter.impl.video.HomeUpdateVideoPresenterImpl
import com.muhoapp.presenter.impl.video.PayAlbumPresenterImpl
import com.muhoapp.presenter.impl.video.TeachVideoPresenterImpl

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

    private val teachVideoPresenter : TeachVideoPresenterImpl by lazy {
        TeachVideoPresenterImpl()
    }

    fun getTeachVideoPresenterImpl() : TeachVideoPresenterImpl{
        return teachVideoPresenter
    }
    private val homeUpdateVideoPresenter : HomeUpdateVideoPresenterImpl by lazy {
        HomeUpdateVideoPresenterImpl()
    }
    fun getHomeUpdateVideoPresenterImpl() : HomeUpdateVideoPresenterImpl{
        return homeUpdateVideoPresenter
    }
    private val payAlbumPresenter : PayAlbumPresenterImpl by lazy {
        PayAlbumPresenterImpl()
    }
    fun getPayAlbumPresenterImpl() : PayAlbumPresenterImpl{
        return payAlbumPresenter
    }
    private val minePresenter : MinePresenterImpl by lazy {
        MinePresenterImpl()
    }
    fun getMinePresenterImpl() : MinePresenterImpl{
        return minePresenter
    }

}