package com.muhoapp.ui.fragment.homeScrean.teach

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.teach.NewTeachData
import com.muhoapp.model.domin.teach.SubjectTeachData
import com.muhoapp.presenter.impl.teach.TeachPagerPresenterImpl
import com.muhoapp.ui.adapter.teach.TeachPagerViewAdapter
import com.muhoapp.utils.PresenterManager
import com.muhoapp.view.teach.ITeachPagerCallback
import com.muhoapp.view.utils.LogUtils
import com.scwang.smart.refresh.layout.api.RefreshLayout

class TeachPagerFragment : BaseFragment<TeachPagerPresenterImpl>(), ITeachPagerCallback {
    @BindView(R.id.teach_pager_view)
    lateinit var teachPagerView : RecyclerView
    @BindView(R.id.teach_pager_view_refreshLayout)
    lateinit var teachPagerViewRefresh : RefreshLayout
    override fun getSubPresenter(): TeachPagerPresenterImpl? {
        return PresenterManager.getTeachPagerPresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_teach_pager
    }

    companion object {
        private const val TEACH_TYPE = "type"
        fun newInstance(type: Int): TeachPagerFragment {
            val bundle = Bundle()
            bundle.putInt(TEACH_TYPE, type)
            val fragment = TeachPagerFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun loadData() {
        when (arguments?.getInt(TEACH_TYPE)) {
            0 -> {
                presenter?.getNewTeachListData(arguments!!.getInt(TEACH_TYPE))
            }
            1, 2, 4 -> {
                presenter?.getSubjectTeachListData(arguments!!.getInt(TEACH_TYPE))
            }
        }
    }

    private var teachPagerViewAdapter : TeachPagerViewAdapter? = null
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)

        teachPagerViewAdapter = TeachPagerViewAdapter()
        teachPagerView.layoutManager = LinearLayoutManager(context)
        teachPagerView.adapter = teachPagerViewAdapter

    }

    override fun bindEvent() {
        presenter?.registerViewCallback(this)
        teachPagerViewRefresh.setOnRefreshListener{
            it.finishRefresh()
        }
        teachPagerViewRefresh.setOnLoadMoreListener {
            when (arguments?.getInt(TEACH_TYPE)) {
                0 -> {
                    presenter?.getMoreNewTeachListData(arguments!!.getInt(TEACH_TYPE))
                }
                1, 2, 4 -> {
                    presenter?.getMoreSubjectTeachListData(arguments!!.getInt(TEACH_TYPE))
                }
            }
        }
    }

    override fun onNewTeachListDataLoad(data: List<SubjectTeachData>, type: Int) {
    }

    override fun onNewTeachListDataLoadMore(data: List<SubjectTeachData>, type: Int) {
    }

    override fun onSubjectTeachDataLoad(data: List<SubjectTeachData>, type: Int) {
        if (type == arguments?.getInt(TEACH_TYPE)){
            teachPagerViewAdapter?.addData(data,type)
        }
    }

    override fun onSubjectTeachDataLoadMore(data: List<SubjectTeachData>, type: Int) {
        if (type == arguments?.getInt(TEACH_TYPE)){
            LogUtils.d(this, "Data --> $data")
            teachPagerViewRefresh.finishLoadMore()
            teachPagerViewAdapter?.addMoreData(data)
        }
    }

    override fun onLoadDataEmpty() {
        teachPagerViewRefresh.finishLoadMore()
    }

    override fun release() {
        presenter?.unregisterViewCallback(this)
        teachPagerViewAdapter?.cleanData()
        teachPagerViewAdapter = null
    }
}