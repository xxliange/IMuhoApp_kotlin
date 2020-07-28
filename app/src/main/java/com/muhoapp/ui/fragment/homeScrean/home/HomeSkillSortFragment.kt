package com.muhoapp.ui.fragment.homeScrean.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.home.SkillContentData
import com.muhoapp.model.domin.home.SkillSortData
import com.muhoapp.presenter.impl.home.HomeSkillSortPresenterImpl
import com.muhoapp.ui.adapter.home.HomeSkillContentAdapter
import com.muhoapp.utils.PresenterManager
import com.muhoapp.view.home.IHomeSkillSortCallback
import com.muhoapp.view.utils.LogUtils

class HomeSkillSortFragment : BaseFragment<HomeSkillSortPresenterImpl>(), IHomeSkillSortCallback {

    @BindView(R.id.home_skillSort_view)
    lateinit var contentView : RecyclerView

    @BindView(R.id.home_skillSort_btn)
    lateinit var btn : TextView

    override fun getSubPresenter(): HomeSkillSortPresenterImpl? {
        return PresenterManager.getHomeSkillSortPresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_home_skill_sort
    }

    companion object {
        private const val SKILLSORT_KW_KEY = "kw"
        fun newInstance(datum: SkillSortData): HomeSkillSortFragment {
            val bundle = Bundle()
            bundle.putString(SKILLSORT_KW_KEY,datum.keyword)
            val fragment = HomeSkillSortFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun bindEvent() {
        presenter?.registerViewCallback(this)
        btn.setOnClickListener{
            LogUtils.d(this, "onClick")
        }
    }

    private var skillContentAdapter : HomeSkillContentAdapter? = null
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)

        skillContentAdapter = HomeSkillContentAdapter()
        contentView.adapter = skillContentAdapter
        contentView.layoutManager = LinearLayoutManager(context)

    }

    override fun loadData() {
        presenter?.getSkillSortContentData(arguments?.getString(SKILLSORT_KW_KEY))
    }

    override fun onSkillSortContentDataLoad(data: List<SkillContentData>,kw:String) {
        if (kw === arguments?.getString(SKILLSORT_KW_KEY)){
            skillContentAdapter?.addData(data)
        }
    }

    override fun release() {
        presenter?.unregisterViewCallback(this)
    }


}