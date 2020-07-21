package com.muhoapp.ui.fragment.homeScrean.mine

import android.view.View
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.presenter.impl.mine.MinePresenterImpl

class MineFragment : BaseFragment<MinePresenterImpl>() {
    override fun getSubPresenter(): MinePresenterImpl? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
        switchUiByState(PageState.SUCCESS)
    }
}