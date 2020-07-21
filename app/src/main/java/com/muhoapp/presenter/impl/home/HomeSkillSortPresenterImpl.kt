package com.muhoapp.presenter.impl.home

import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.Api
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.home.SkillContentData
import com.muhoapp.presenter.presenterInterface.IHomeSkillSortPresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.home.IHomeSkillSortCallback
import com.muhoapp.view.utils.LogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeSkillSortPresenterImpl : IBaseViewPresenter<IHomeSkillSortCallback>(),
    IHomeSkillSortPresenter {
    override fun getSkillSortContentData(kw: String?) {
        RetrofitManger.instance!!.getApi()
            .getSkillContent(kw!!, 1, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<SkillContentData>>() {
                override fun onFailure(e: Throwable?, message: String) {
                }

                override fun onSuccess(data: List<SkillContentData>) {
                    handlerContentResponse(data,kw)
                }
            })
    }

    private fun handlerContentResponse(data: List<SkillContentData>, kw: String) {
        for (callback in callbacks){
            callback.onSkillSortContentDataLoad(data, kw)
        }
    }
}