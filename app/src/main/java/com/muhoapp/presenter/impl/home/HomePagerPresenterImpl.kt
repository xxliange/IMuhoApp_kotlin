package com.muhoapp.presenter.impl.home

import android.util.Log
import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.home.*
import com.muhoapp.presenter.presenterInterface.IHomePagerPresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.home.IHomePagerCallback
import com.muhoapp.view.utils.LogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePagerPresenterImpl: IHomePagerPresenter, IBaseViewPresenter<IHomePagerCallback>() {
    private val TAG = "HomePagerPresenterImpl"
    override fun getLooperData() {
        val api = RetrofitManger.instance!!.getApi()
            .getBannerDataOb(2,1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<BannerData>>() {
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<BannerData>) {
                    for (callback in callbacks){
                        callback.onLooperDataLoad(data)
                    }
                }

            })
    }

    override fun getStarData() {
        RetrofitManger.instance!!.getApi()
            .getStarData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<StarData>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<StarData>) {
                    for (callback in callbacks){
                        callback.onStarDataLoad(data)
                    }
                }

            })
    }

    override fun getPayAlbumData() {
        RetrofitManger.instance!!.getApi()
            .getPayAlbumData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<PayAlbumData>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<PayAlbumData>) {
                    for(callback in callbacks){
                        callback.onPayAlbumDataLoad(data)
                    }
                }

            })
    }

    override fun getSkillSort() {
        RetrofitManger.instance!!.getApi()
            .getSkillSort()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<SkillSortData>>(){
                override fun onFailure(e: Throwable?, message: String) {
                }

                override fun onSuccess(data: List<SkillSortData>) {
                    for (callback in callbacks){
                        callback.onSkillSortDataLoad(data)
                    }
                }

            })
    }

    override fun getSkillSortContent(kw:String) {
        RetrofitManger.instance!!.getApi()
            .getSkillContent(kw, 1, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<SkillContentData>>() {
                override fun onFailure(e: Throwable?, message: String) {
                }

                override fun onSuccess(data: List<SkillContentData>) {
//                    handlerContentResponse(data,kw)
                    for(callback in callbacks){
                        callback.onSkillSortContentDataLoad(data)
                    }

                }
            })
    }

    override fun getPrivateTeach() {
        RetrofitManger.instance!!.getApi()
            .getPrivateTeach(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<PrivateTeachData>>(){
                override fun onSuccess(data: List<PrivateTeachData>) {
                    for(callback in callbacks){
                        callback.onPrivateTeachDataLoad(data)
                    }
                }

                override fun onFailure(e: Throwable?, message: String) {
                }

            })
    }
}