package com.muhoapp.ui.fragment.homeScrean.mine

import android.view.View
import android.widget.LinearLayout
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.presenter.impl.mine.MinePresenterImpl
import com.muhoapp.utils.Utils

class MineFragment : BaseFragment<MinePresenterImpl>() {
    @BindView(R.id.mine_container)
    lateinit var mineContainer : LinearLayout
    override fun getSubPresenter(): MinePresenterImpl? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
        switchUiByState(PageState.SUCCESS)
        val statusBarHeight = Utils.getStatusBarHeight(context)
        mineContainer.setPadding(0,statusBarHeight,0,0)
    }
}