package com.muhoapp.ui.fragment.homeScrean.home

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.home.BannerData
import com.muhoapp.presenter.impl.home.HomePresenterImpl
import com.muhoapp.utils.PresenterManager
import com.muhoapp.utils.Utils
import com.muhoapp.view.adapter.home.HomeLooperAdapter
import com.muhoapp.view.adapter.home.HomePagerAdapter
import com.muhoapp.view.home.IHomeCallback

class HomeFragment : BaseFragment<HomePresenterImpl>(), IHomeCallback {

    private var TAG = "HomeFragment"

//    @BindView(R.id.home_looper)
//    lateinit var looper : ViewPager

    @BindView(R.id.home_tab)
    lateinit var homeTab: TabLayout

    @BindView(R.id.home_view)
    lateinit var homeView: ViewPager

    @BindView(R.id.home_tab_container)
    lateinit var home_tab_container: LinearLayout

    private var homePagerAdapter: HomePagerAdapter? = null
    private var looperAdapter: HomeLooperAdapter? = null
    private var tabList = ArrayList<Int>()
    private var va: ValueAnimator? = null

    //页面初始化
    override fun initView(rootView: View) {
        super.initView(rootView)
        switchUiByState(PageState.SUCCESS)
        val statusBarHeight = Utils.getStatusBarHeight(context)
        home_tab_container.setPadding(0, statusBarHeight, 0, 20)
        tabList.add(0)
        tabList.add(1)

        homeTab.setupWithViewPager(homeView)

        homePagerAdapter = HomePagerAdapter(childFragmentManager)
        homeView.adapter = homePagerAdapter
        homePagerAdapter?.addData(tabList)

        val tabCount = homeTab.tabCount
        for (i in 0 until tabCount) {
            val tabAt = homeTab.getTabAt(i)
            if (tabAt !== null) {
                tabAt.setCustomView(homePagerAdapter!!.getTabView(i, context))
            }
        }
//        mAlbumInfoPagerAdapter = new AlbumInfoPagerAdapter(getChildFragmentManager());
//        albumPager.setAdapter(mAlbumInfoPagerAdapter);
//        mAlbumInfoPagerAdapter.addData(tabList);
//        val window = activity?.window
//        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window?.statusBarColor = resources.getColor(android.R.color.white)
//        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        looperAdapter = HomeLooperAdapter()
//        looper.adapter = looperAdapter
    }


    //加载数据
    override fun loadData() {
        presenter?.getBannerData()
    }

    //绑定数据
    override fun bindEvent() {
        super.bindEvent()
        presenter?.registerViewCallback(this)
        homeView.currentItem = 0
        val customView1 = homeTab.getTabAt(0)?.customView
        val firstTitle: TextView = customView1!!.findViewById(R.id.item_home_tab_title)
        firstTitle.textSize = 18F
        firstTitle.setTextColor(context!!.getColor(R.color.colorBlack))
        firstTitle.typeface = Typeface.DEFAULT_BOLD
        homeTab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                val customView = tab.customView
                val title: TextView = customView!!.findViewById(R.id.item_home_tab_title)

                title.setTextColor(context!!.getColor(R.color.colorBlack))
                title.typeface = Typeface.DEFAULT_BOLD
                va = ValueAnimator.ofInt(14, 18)
                va!!.addUpdateListener {
                    val animatedValue = it.animatedValue
                    title.textSize = animatedValue.toString().toFloat()
                }
                va!!.duration = 100
                va!!.start()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val customView = tab.customView
                val title: TextView = customView!!.findViewById(R.id.item_home_tab_title)
                title.setTextColor(context!!.getColor(R.color.colorTextLow))
                title.typeface = Typeface.DEFAULT
                va = ValueAnimator.ofInt(18, 14)
                va!!.addUpdateListener {
                    val animatedValue = it.animatedValue
                    title.textSize = animatedValue.toString().toFloat()
                }
                va!!.duration = 100
                va!!.start()
            }
        })
    }

    override fun getSubPresenter(): HomePresenterImpl? {
        return PresenterManager.getHomePresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onLoadBannerData(data: BannerData?) {
        Log.d(TAG, "BannerData --> $data")
        looperAdapter?.setData(data)

    }

    override fun release() {
        presenter?.unregisterViewCallback(this)
        va = null
    }
}