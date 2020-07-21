package com.muhoapp.utils

import android.content.Context

object Utils {
    fun getStatusBarHeight(context: Context?): Int {
        var statusBarHeight = 0
        if (context !== null) {
        val identifier = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (identifier > 0) {
                statusBarHeight = context.resources.getDimensionPixelSize(identifier)
            }
        }
        return statusBarHeight
    }
}