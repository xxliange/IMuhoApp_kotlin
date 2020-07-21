package com.muhoapp.ui.fragment.homeScrean.hot

import android.view.View
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.presenter.impl.hot.HotPresenterImpl

class HotFragment : BaseFragment<HotPresenterImpl>() {
    override fun getSubPresenter(): HotPresenterImpl? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_hot
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
        switchUiByState(PageState.SUCCESS)
    }
}