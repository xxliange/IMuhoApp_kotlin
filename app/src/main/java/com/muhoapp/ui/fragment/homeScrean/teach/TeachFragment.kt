package com.muhoapp.ui.fragment.homeScrean.teach

import android.util.Log
import android.view.View
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.presenter.impl.teach.TeachPresenterImpl

class TeachFragment : BaseFragment<TeachPresenterImpl>() {
    private var TAG = "HomeFragment"
    override fun getSubPresenter(): TeachPresenterImpl? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_teach
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
        switchUiByState(PageState.SUCCESS)
    }
}