package com.muhoapp.ui.fragment.homeScrean.home

import android.view.View
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.presenter.impl.home.HomePresenterImpl

class HomeUpdateFragment : BaseFragment<HomePresenterImpl>() {
    override fun getSubPresenter(): HomePresenterImpl? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_home_update
    }

    companion object{
        fun newInstance():HomeUpdateFragment{
            return HomeUpdateFragment()
        }
    }

    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)
    }

}