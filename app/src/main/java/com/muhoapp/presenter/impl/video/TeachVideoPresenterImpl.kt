package com.muhoapp.presenter.impl.video

import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.model.domin.video.TeachVideoMoreRandom
import com.muhoapp.model.domin.video.VideoCollectInfo
import com.muhoapp.presenter.presenterInterface.ITeachVideoPresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.teach.ITeachVideoCallback
import com.muhoapp.view.utils.LogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TeachVideoPresenterImpl : IBaseViewPresenter<ITeachVideoCallback>(), ITeachVideoPresenter {
    /**
     * 加载视频列表数据
     */
    override fun getTeachVideoListData() {
        RetrofitManger.instance!!.getApi()
            .getTeachVideoListData(25)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<TeachVideoListData>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<TeachVideoListData>) {
                    for (callback in callbacks){
                        callback.onLoadTeachVideoList(data)
                    }
                }

            })
    }

    /**
     * 加载更多推荐
     */
    override fun getTeachVideoMoreRandom() {
        RetrofitManger.instance!!.getApi()
            .getTeachVideoMoreRandom()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<TeachVideoMoreRandom>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<TeachVideoMoreRandom>) {
                    for (callback in callbacks){
                        callback.onLoadTeachMoreRandom(data)
                    }
                }
            })

    }

    /**
     * 用户观看数加一
     */
    override fun getViewVideo() {
        RetrofitManger.instance!!.getApi()
            .getVideoView(849,2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<String>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: String) {
                    LogUtils.d(this, "data --> $data")
                }

            })
    }

    override fun getVideoCollect(cid:Int) {
        RetrofitManger.instance!!.getApi()
            .getVideoCollectInfo(cid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<VideoCollectInfo>(){
                override fun onFailure(e: Throwable?, message: String) {
                }

                override fun onSuccess(data: VideoCollectInfo) {
                    for (callback in callbacks){
                        callback.onLoadVideoCollect(data)
                    }
                }

            })
    }

    /**
     * 用户点击收藏取消收藏
     */
    override fun getVideoUserCollect(cid: Int, type: Int, c_type: Int) {
        RetrofitManger.instance!!.getApi()
            .getVideoUserCollect(cid, type, c_type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<String>(){
                override fun onFailure(e: Throwable?, message: String) {
                }

                override fun onSuccess(data: String) {
                    for(callback in callbacks){
                        callback.onLoadVideoUserCollect(data)
                    }
                }

            })
    }
}