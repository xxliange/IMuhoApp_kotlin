package com.muhoapp.presenter.impl.home

import android.util.Log
import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.home.BannerData
import com.muhoapp.model.domin.home.PayAlbumData
import com.muhoapp.model.domin.home.SkillSortData
import com.muhoapp.model.domin.home.StarData
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
}