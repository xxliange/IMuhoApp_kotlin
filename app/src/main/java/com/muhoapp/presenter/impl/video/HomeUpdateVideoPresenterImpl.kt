package com.muhoapp.presenter.impl.video

import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.video.HomeUpdateVideoData
import com.muhoapp.model.domin.video.HomeUpdateVideoMoreData
import com.muhoapp.presenter.presenterInterface.IHomeUpdateVideoPresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.utils.LogUtils
import com.muhoapp.view.video.HomeUpdateVideoCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeUpdateVideoPresenterImpl : IBaseViewPresenter<HomeUpdateVideoCallback>(), IHomeUpdateVideoPresenter {
    override fun getVideoInfo(cid: Int) {
        RetrofitManger.instance!!.getApi()
            .getUpdateVideoInfo(cid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<HomeUpdateVideoData>(){
                override fun onSuccess(data: HomeUpdateVideoData) {
                    for (callback in callbacks){
                        callback.onLoadVideoInfo(data)
                    }
                }
                override fun onFailure(e: Throwable?, message: String) {
                }

            })
    }

    override fun getVideoMore() {
        RetrofitManger.instance!!.getApi()
            .getUpdateMoreVideo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<HomeUpdateVideoMoreData>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<HomeUpdateVideoMoreData>) {
                    for (callback in callbacks){
                        callback.onLoadMoreVideo(data)
                    }
                }

            })
    }
}