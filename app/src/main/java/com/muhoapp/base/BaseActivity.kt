package com.muhoapp.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseActivity<T> : FragmentActivity() {

    private var unBinder: Unbinder? = null
    protected var presenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        presenter = getSubPresenter()
        unBinder = ButterKnife.bind(this)
        //初始化布局
        initView()
        //初始化事件监听
        initListener()
        //初始化数据加载
        bindData()
        //如果是视频页面 初始化视频
    }

    abstract fun getSubPresenter(): T?

    override fun onDestroy() {
        super.onDestroy()
        unBinder?.unbind()
        release()
    }

    open fun release() {

    }

    open fun bindData() {

    }

    open fun initListener() {

    }

    open fun initView() {

    }

    abstract fun getContentView(): Int

    /**
     * 设置沉浸式体验 去掉顶部状态栏高度
     * @param isLight 如果未true 则显示黑色 false为白色
     */
    protected open fun setTransparentStatusBar(isLight: Boolean) {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (isLight) {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        //        val window = activity?.window
//        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window?.statusBarColor = resources.getColor(android.R.color.white)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

}