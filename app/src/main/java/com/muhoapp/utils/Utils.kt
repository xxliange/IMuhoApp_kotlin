package com.muhoapp.utils

import android.content.Context
import android.util.TypedValue

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

    fun dp2px(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()

    }
}