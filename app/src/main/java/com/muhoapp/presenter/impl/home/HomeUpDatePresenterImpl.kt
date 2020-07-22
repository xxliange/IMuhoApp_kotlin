package com.muhoapp.presenter.impl.home

import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.home.UpdateData
import com.muhoapp.presenter.presenterInterface.IHomeUpDatePresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.home.IHomeUpDateCallback
import com.muhoapp.view.utils.LogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeUpDatePresenterImpl : IBaseViewPresenter<IHomeUpDateCallback>(), IHomeUpDatePresenter {
    private var contentL = 1
    override fun getUpDateContent() {
        RetrofitManger.instance!!.getApi()
            .getUpDateContent(contentL)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<UpdateData>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<UpdateData>) {
                    for(callback in callbacks){
                        callback.onLoadUpDateContent(data)
                    }
                }

            })

    }

    override fun getMoreUpDateContent() {
        contentL++
        RetrofitManger.instance!!.getApi()
            .getUpDateContent(contentL)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<UpdateData>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<UpdateData>) {
                    for(callback in callbacks){
                        callback.onLoadMoreUpDateContent(data)
                    }
                }

            })
    }
}