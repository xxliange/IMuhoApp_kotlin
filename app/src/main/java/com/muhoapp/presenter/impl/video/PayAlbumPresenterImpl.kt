package com.muhoapp.presenter.impl.video

import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.home.PayAlbumData
import com.muhoapp.model.domin.video.PayAlbumInfoData
import com.muhoapp.model.domin.video.PayAlbumInfoMoreData
import com.muhoapp.model.domin.video.PayAlbumVideoCollectData
import com.muhoapp.model.domin.video.PayAlbumVideoData
import com.muhoapp.presenter.presenterInterface.IPayAlbumPresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.utils.LogUtils
import com.muhoapp.view.video.PayAlbumVideoCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PayAlbumPresenterImpl : IBaseViewPresenter<PayAlbumVideoCallback>(), IPayAlbumPresenter {
    override fun getVideoList(sid: Int) {
        RetrofitManger.instance!!.getApi()
            .getPayAlbumListData(sid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<PayAlbumVideoData>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<PayAlbumVideoData>) {
                    for (callback in callbacks){
                        callback.onLoadVideoList(data)
                    }
                }

            })
    }

    override fun getVideoInfo(id: Int) {
        RetrofitManger.instance!!.getApi()
            .getPayAlbumInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<PayAlbumInfoData>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: PayAlbumInfoData) {
                    for (callback in callbacks){
                        callback.onLoadVideoInfo(data)
                    }
                }

            })
    }

    override fun getVideoMoreData() {
        RetrofitManger.instance!!.getApi()
            .getPayAlbumMoreData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<PayAlbumInfoMoreData>>(){
                override fun onFailure(e: Throwable?, message: String) {
                }

                override fun onSuccess(data: List<PayAlbumInfoMoreData>) {
                    for (callback in callbacks){
                        callback.onLoadMoreVideo(data)
                    }
                }

            })
    }

    override fun getVideoCollect(cid: Int) {
        RetrofitManger.instance!!.getApi()
            .getPayAlbumCollect(cid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<PayAlbumVideoCollectData>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: PayAlbumVideoCollectData) {
                    for (callback in callbacks){
                        callback.onLoadCollectInfo(data)
                    }
                }

            })
    }
}