package com.muhoapp.presenter.impl.teach

import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.teach.NewTeachData
import com.muhoapp.model.domin.teach.SubjectTeachData
import com.muhoapp.presenter.presenterInterface.ITeachPagerPresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.teach.ITeachPagerCallback
import com.muhoapp.view.utils.LogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TeachPagerPresenterImpl : IBaseViewPresenter<ITeachPagerCallback>(), ITeachPagerPresenter {
    companion object {
        const val DEFAULT_PAGE = 1
    }
    private var isLoadingMore = false
    private val pageInfo: HashMap<Int, Int> = HashMap()
    override fun getNewTeachListData(type: Int) {
        pageInfo[type] = DEFAULT_PAGE
        RetrofitManger.instance!!.getApi()
            .getNewTeachData(DEFAULT_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<SubjectTeachData>>() {
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<SubjectTeachData>) {
                    for (callback in callbacks) {
                        callback.onSubjectTeachDataLoad(data, type)
                    }
                }
            })
    }

    override fun getMoreNewTeachListData(type: Int) {
        if (isLoadingMore) {
            return
        }
        isLoadingMore = true
        val lastPage = pageInfo[type]
        if (lastPage != null) {
            val currentPage = lastPage + 1
            pageInfo[type] = currentPage
            RetrofitManger.instance!!.getApi()
                .getNewTeachData(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<SubjectTeachData>>() {
                    override fun onFailure(e: Throwable?, message: String) {

                    }

                    override fun onSuccess(data: List<SubjectTeachData>) {
                        isLoadingMore = false
                        for (callback in callbacks) {
                            callback.onSubjectTeachDataLoadMore(data, type)
                        }
                    }
                })
        }
    }

    override fun getSubjectTeachListData(type: Int) {
        pageInfo[type] = DEFAULT_PAGE
        RetrofitManger.instance!!.getApi()
            .getSubjectTeachData(DEFAULT_PAGE, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<SubjectTeachData>>() {
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<SubjectTeachData>) {
                    for (callback in callbacks) {
                        callback.onSubjectTeachDataLoad(data, type)
                    }
                }
            })
    }

    override fun getMoreSubjectTeachListData(type: Int) {
        if (isLoadingMore) {
            return
        }
        isLoadingMore = true
        val lastPage = pageInfo[type]
        if (lastPage != null) {
            val currentPage = lastPage + 1
            pageInfo[type] = currentPage
            RetrofitManger.instance!!.getApi()
                .getSubjectTeachData(currentPage, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<SubjectTeachData>>() {
                    override fun onFailure(e: Throwable?, message: String) {

                    }

                    override fun onSuccess(data: List<SubjectTeachData>) {
                        isLoadingMore = false
                        if (data.isEmpty()) {
                            for (callback in callbacks) {
                                callback.onLoadDataEmpty()
                            }
                        } else {
                            for (callback in callbacks) {
                                callback.onSubjectTeachDataLoadMore(data, type)
                            }
                        }
                    }
                })
        }
    }
}