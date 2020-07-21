package com.muhoapp.presenter.impl.home

import android.util.Log
import com.muhoapp.base.IBaseViewPresenter
import com.muhoapp.model.BaseObserver
import com.muhoapp.model.domin.home.BannerData
import com.muhoapp.presenter.presenterInterface.IHomePresenter
import com.muhoapp.utils.RetrofitManger
import com.muhoapp.view.home.IHomeCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenterImpl: IHomePresenter, IBaseViewPresenter<IHomeCallback>() {
    private val TAG = "HomePresenterImpl"
    override fun getBannerData() {
        val api = RetrofitManger.instance!!.getApi()
            .getBannerDataOb(2,1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<BannerData>>(){
                override fun onFailure(e: Throwable?, message: String) {
                    Log.d(TAG,"data --> $message")
                }

                override fun onSuccess(data: List<BannerData>) {
                    Log.d(TAG,"data --> ${data.size}")
                }
            })

//        api.getBannerData(2,1)
//        bannerData?.enqueue(object : Callback<BannerData>{
//            override fun onFailure(call: Call<BannerData>, t: Throwable) {
//                Log.d(TAG, "onFailure --> ${t.message}")
//            }
//
//            override fun onResponse(
//                call: Call<BannerData>,
//                response: Response<BannerData>
//            ) {
//                val code = response.code()
//                if (code == HttpURLConnection.HTTP_OK) {
//                    val body = response.body()
//                    updataCategorise(body)
//                }h
//            }
//        })
    }

    private fun updataCategorise(body: BannerData?) {
        for (callback in callbacks){
        Log.d(TAG, "in")
            callback.onLoadBannerData(body)
        }
    }


}