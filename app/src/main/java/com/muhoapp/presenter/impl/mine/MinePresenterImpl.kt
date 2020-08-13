package com.muhoapp.presenter.impl.mine

import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.home.MineUserCollect
import com.muhoapp.model.domin.home.MineUserHistory
import com.muhoapp.presenter.presenterInterface.IMinePresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.home.IMineCallback
import com.muhoapp.view.utils.LogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MinePresenterImpl : IBaseViewPresenter<IMineCallback>(), IMinePresenter {
    override fun getUserHistory() {
        RetrofitManger.instance!!.getApi()
            .getMineUserHistoryData(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<MineUserHistory>>(){
                override fun onFailure(e: Throwable?, message: String) {
                }

                override fun onSuccess(data: List<MineUserHistory>) {
                    for(callback in callbacks){
                        callback.onLoadUserHistory(data)
                    }
                }

            })
    }

    override fun getUserCollect() {
        RetrofitManger.instance!!.getApi()
            .getMineUserCollect(1,1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<MineUserCollect>>(){
                override fun onFailure(e: Throwable?, message: String) {

                }

                override fun onSuccess(data: List<MineUserCollect>) {
                    for (callback in callbacks){
                        callback.onLoadUserCollect(data)
                    }
                }

            })
    }
}