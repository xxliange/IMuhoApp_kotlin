package com.muhoapp.ui.fragment.homeScrean.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.home.UpdateData
import com.muhoapp.presenter.impl.home.HomePresenterImpl
import com.muhoapp.presenter.impl.home.HomeUpDatePresenterImpl
import com.muhoapp.ui.adapter.home.HomeUpDateAdapter
import com.muhoapp.utils.PresenterManager
import com.muhoapp.view.home.IHomeUpDateCallback
import com.muhoapp.view.utils.LogUtils
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener

class HomeUpdateFragment : BaseFragment<HomeUpDatePresenterImpl>(), IHomeUpDateCallback {
    @BindView(R.id.home_update_view)
    lateinit var upDateView : RecyclerView
    @BindView(R.id.refreshLayout)
    lateinit var refreshLayout : RefreshLayout
    override fun getSubPresenter(): HomeUpDatePresenterImpl? {
        return PresenterManager.getHomeUpDatePresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_home_update
    }

    companion object{
        fun newInstance():HomeUpdateFragment{
            return HomeUpdateFragment()
        }
    }

    private var upDateAdapter : HomeUpDateAdapter? = null
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)

        val linearLayoutManager = LinearLayoutManager(context)
        upDateView.layoutManager = linearLayoutManager
        upDateAdapter = HomeUpDateAdapter()
        upDateView.adapter = upDateAdapter
    }

    override fun bindEvent() {
        presenter?.registerViewCallback(this)
        refreshLayout.setOnLoadMoreListener {
            presenter?.getMoreUpDateContent()
        }
        refreshLayout.setOnRefreshListener{
            it.finishRefresh()
        }
    }

    override fun loadData() {
        presenter?.getUpDateContent()
    }

    /**
     * 获取最新更新数据
     */
    override fun onLoadUpDateContent(data: List<UpdateData>) {
        upDateAdapter?.addData(data)
    }

    /**
     * 获取更多最新更新数据
     */
    override fun onLoadMoreUpDateContent(data: List<UpdateData>) {
        refreshLayout.finishLoadMore()
        upDateAdapter?.addMoreData(data)

    }

}