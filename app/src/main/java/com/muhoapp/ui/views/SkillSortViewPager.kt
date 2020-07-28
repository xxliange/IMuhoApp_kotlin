package com.muhoapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class SkillSortViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    private var isScroll = false

    constructor(context: Context) : this(context, null)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (isScroll) {
            super.onInterceptTouchEvent(ev)
        } else {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (isScroll) {
            super.onTouchEvent(ev)
        } else {
            true
        }
    }

    fun setScroll(scroll: Boolean) {
        isScroll = scroll
    }
}

