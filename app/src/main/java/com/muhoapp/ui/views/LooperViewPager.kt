package com.muhoapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.viewpager.widget.ViewPager
import com.muhoapp.view.utils.FixedSpeedScroller
import com.muhoapp.view.utils.LogUtils
import java.lang.Exception

class LooperViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    constructor(context: Context) : this(context, null)

    /**
     * 滚动延迟
     */
    private val loopFrequent = 3000L
    /**
     * 滚动的速度
     */
    private val scrollerDuration = 1200

    init {
        try {
            val clazz = Class.forName("androidx.viewpager.widget.ViewPager")
            val field = clazz.getDeclaredField("mScroller")
            val fixedSpeedScroller = FixedSpeedScroller(context, LinearOutSlowInInterpolator())
            fixedSpeedScroller.setmDuration(scrollerDuration)
            field.isAccessible = true
            field.set(this, fixedSpeedScroller)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val task: Runnable = object : Runnable {
        override fun run() {
            currentItem++
            postDelayed(this, loopFrequent)
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                removeCallbacks(task)
            }
            MotionEvent.ACTION_UP -> {
                postDelayed(task, loopFrequent)
            }
            MotionEvent.ACTION_CANCEL -> {
                postDelayed(task, loopFrequent)
            }
        }
        return super.onTouchEvent(ev)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        LogUtils.d(this, "onAttachedToWindow")
        post(task)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        LogUtils.d(this, "onDetachedFromWindow")
        removeCallbacks(task)
    }

}