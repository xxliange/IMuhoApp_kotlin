package com.muhoapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import com.muhoapp.R

abstract class BaseFragment<P> : Fragment() {

    enum class PageState {
        NONE, LOADING, ERROR, EMPTY, SUCCESS
    }

    private var bind: Unbinder? = null

    protected var presenter: P? = null

    private var rootView: ViewGroup? = null

    abstract fun getSubPresenter(): P?

    private var successView : View ? = null
    private var loadingView : View ? = null
    private var errorView : View ? = null
    private var emptyView : View ? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = getRootView(inflater, container)
        val containerView = rootView?.findViewById<ViewGroup>(R.id.fragment_container)
        setUpView(rootView, containerView, inflater)
        presenter = getSubPresenter()
        bindEvent()
        loadData()
        return rootView
    }

    open fun getRootView(inflater: LayoutInflater, container: ViewGroup?): ViewGroup? {
        return inflater.inflate(R.layout.fragment_container,container,false) as ViewGroup
    }


    open fun setUpView(
        rootView: ViewGroup?,
        containerView: ViewGroup?,
        inflater: LayoutInflater
    ) {
       //成功的view
        successView = subCreateView(inflater, containerView)
        containerView?.addView(successView)

        switchUiByState(PageState.NONE)
        bind = ButterKnife.bind(this, rootView!!)
        initView(successView!!)
    }

    fun switchUiByState(state: PageState) {
        successView?.visibility = if(state == PageState.SUCCESS) View.VISIBLE else View.GONE
        if (state == PageState.NONE) {
            successView?.visibility = View.GONE
        }
    }

    open fun initView(rootView: View) {}

    private fun subCreateView(inflater: LayoutInflater, containerView: ViewGroup?): View? {
        return inflater.inflate(getPageLayoutId(), containerView, false)
    }

    abstract fun getPageLayoutId(): Int

    open fun loadData() {}

    open fun bindEvent() {}

    override fun onDestroyView() {
        super.onDestroyView()
        bind?.unbind()
        release()
    }

    open fun release() {}


}