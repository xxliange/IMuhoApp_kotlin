package com.muhoapp.ui.fragment.homeScrean.vip

import android.view.View
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.presenter.impl.vip.VipPresenterImpl

class VipFragment : BaseFragment<VipPresenterImpl>() {
    override fun getSubPresenter(): VipPresenterImpl? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_vip
    }
    override fun initView(rootView: View) {
        super.initView(rootView)
        switchUiByState(PageState.SUCCESS)
    }
}