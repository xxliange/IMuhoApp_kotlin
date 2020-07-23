package com.muhoapp.ui.fragment.homeScrean.teach

import android.view.View
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.presenter.impl.teach.TeachPresenterImpl
import com.muhoapp.ui.adapter.teach.TeachPagerAdapter
import com.muhoapp.utils.Utils

class TeachFragment : BaseFragment<TeachPresenterImpl>() {

    @BindView(R.id.teach_tab)
    lateinit var teachTab : TabLayout

    @BindView(R.id.teach_view)
    lateinit var teachView : ViewPager

    @BindView(R.id.teach_container)
    lateinit var container : LinearLayout

    private var tabList = ArrayList<Int>()

    override fun getSubPresenter(): TeachPresenterImpl? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_teach
    }

    private var teachPagerAdapter : TeachPagerAdapter? = null
    override fun initView(rootView: View) {
        super.initView(rootView)
        switchUiByState(PageState.SUCCESS)
        val statusBarHeight = Utils.getStatusBarHeight(context)
        container.setPadding(0,statusBarHeight,0,0)
        if(tabList.size<4){
            tabList.add(0)
            tabList.add(1)
            tabList.add(4)
            tabList.add(2)
        }
        teachTab.setupWithViewPager(teachView)
        teachPagerAdapter = TeachPagerAdapter(childFragmentManager)
        teachView.adapter = teachPagerAdapter
        teachPagerAdapter?.addData(tabList)
    }
}