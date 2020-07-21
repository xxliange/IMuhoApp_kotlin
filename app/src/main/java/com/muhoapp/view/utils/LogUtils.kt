package com.muhoapp.view.utils

import android.util.Log

object LogUtils {
    private var currentLey = 4
    private const val debugLev = 4
    private const val infoLev = 3
    private const val warningLev = 2
    private const val errorLev = 1

    fun d(clazz: Any, msg: String) {
        if (currentLey >= debugLev) {
            Log.d(clazz.javaClass.name, " $msg")
        }
    }
    fun w(clazz: Any, msg: String) {
        if (currentLey >= warningLev) {
            Log.w(clazz.javaClass.name, " $msg")
        }
    }
    fun i(clazz: Any, msg: String) {
        if (currentLey >= infoLev) {
            Log.i(clazz.javaClass.name, " $msg")
        }
    }
    fun e(clazz: Any, msg: String) {
        if (currentLey >= errorLev) {
            Log.e(clazz.javaClass.name, " $msg")
        }
    }
}