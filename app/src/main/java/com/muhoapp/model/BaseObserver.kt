package com.muhoapp.model

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T> : Observer<BaseResponse<T>>  {
    private val TAG = "BaseObserver"
    override fun onComplete() {
        Log.i(TAG, "onComplete")
    }

    override fun onNext(t: BaseResponse<T>) {
        if (t.stateCode == 200) {
            onSuccess(t.data)
        }else{
            onFailure(null, t.message)
        }
    }
    protected abstract fun onFailure(e : Throwable?, message: String)

    protected abstract fun onSuccess(data: T)

    override fun onError(e: Throwable) {
        Log.d(TAG, "onError${e.message}")
    }

    override fun onSubscribe(d: Disposable) {
        Log.d(TAG, "onSubscribe")
    }
}