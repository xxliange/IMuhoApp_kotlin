package com.muhoapp.base

import com.muhoapp.utils.RetrofitManger

open class IBaseViewPresenter<C> {

    protected val callbacks: ArrayList<C> = ArrayList()

    open fun registerViewCallback(callback: C) {
        if (!callbacks.contains(callback)) {
            callbacks.add(callback)
        }
    }

    open fun unregisterViewCallback(callback: C) {
        callbacks.remove(callback)
    }

    protected val mApi by lazy {
        RetrofitManger.instance?.getApi()
    }
}