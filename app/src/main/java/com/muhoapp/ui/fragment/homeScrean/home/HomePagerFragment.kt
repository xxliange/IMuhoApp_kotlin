package com.muhoapp.ui.fragment.homeScrean.home

import android.content.Context.MODE_PRIVATE
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.home.*
import com.muhoapp.presenter.impl.home.HomePagerPresenterImpl
import com.muhoapp.ui.adapter.home.*
import com.muhoapp.utils.CacheUtils
import com.muhoapp.utils.PresenterManager
import com.muhoapp.utils.SaveSharePreferences
import com.muhoapp.utils.Utils
import com.muhoapp.view.home.IHomePagerCallback
import com.muhoapp.view.utils.LogUtils
import com.scwang.smart.refresh.layout.api.RefreshLayout
import java.io.File
import java.lang.reflect.Type

class HomePagerFragment : BaseFragment<HomePagerPresenterImpl>(), IHomePagerCallback {

    private var mData = ArrayList<BannerData>()

    @BindView(R.id.home_looper_title)
    lateinit var looperTitle: TextView

    @BindView(R.id.home_looper_pager)
    lateinit var looper: ViewPager

    @BindView(R.id.home_star_view)
    lateinit var starView: RecyclerView

    @BindView(R.id.home_payAlbum_view)
    lateinit var payAlbumView: RecyclerView

    @BindView(R.id.home_column_view)
    lateinit var columnView: RecyclerView

    @BindView(R.id.home_private_teach_view)
    lateinit var privateTeachView: RecyclerView

    @BindView(R.id.home_sort_tab)
    lateinit var sortTab: TabLayout

    @BindView(R.id.home_sort_view)
    lateinit var sortView: ViewPager

    @BindView(R.id.home_del_cache)
    lateinit var btn : Button

    @BindView(R.id.refreshLayout)
    lateinit var refreshLayout : RefreshLayout

    @BindView(R.id.home_looper_container)
    lateinit var looperContainer : RelativeLayout

    private var windowManager : WindowManager? = null

    override fun getSubPresenter(): HomePagerPresenterImpl? {
        return PresenterManager.getHomePagerPresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_home_pager
    }

    companion object {
        fun newInstance(): HomePagerFragment {
            return HomePagerFragment()
        }
    }

    /**
     * 绑定事件
     */
    override fun bindEvent() {
        presenter?.registerViewCallback(this)
        looper.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                var i = position % mData.size
                looperTitle.text = mData.get(i).title
            }

        })
        btn.setOnClickListener{
            CacheUtils.deleteFileByDir(CacheUtils.CacheType.PREFERENCES,context)
        }
    }

    private var looperAdapter: HomeLooperPagerAdapter? = null
    private var starAdapter: HomeStarListAdapter? = null
    private var payAlbumAdapter: HomePayAlbumListAdapter? = null
    private var columnAdapter: HomeColumnAdapter? = null
    private var skillSortAdpater: HomeSkillSortPagerAdapter? = null
    private var privateTeachAdapter: HomePrivateTeachAdapter? = null

    /**
     * 初始化view
     */
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)

        setLooperAdapter()
        setStarAdapter()
        setPayAlbumAdapter()
        setColumnAdpater()
        setSortAdapter()
        setPrivateTeachAdapter()

        SaveSharePreferences.initSP(context, "homeData")
        LogUtils.d(this, CacheUtils.getCacheSize(context))

        windowManager = activity?.windowManager
        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val widthPixels = displayMetrics.widthPixels
        LogUtils.d(this, "${Utils.px2dip(context!!,widthPixels.toFloat())}")

        val marginLayoutParams = ViewGroup.MarginLayoutParams(looperContainer.layoutParams)
        val layoutParams = LinearLayout.LayoutParams(marginLayoutParams)
        layoutParams.height = widthPixels/2
        looperContainer.layoutParams = layoutParams

//        CacheUtils.deleteFileByDir(CacheUtils.CacheType.PREFERENCES,context)
    }

    private fun setLooperAdapter() {
        looperAdapter = HomeLooperPagerAdapter()
        looper.adapter = looperAdapter
    }

    private fun setStarAdapter() {
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        starView.layoutManager = linearLayout
        starAdapter = HomeStarListAdapter()
        starView.adapter = starAdapter
        starView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = 30
            }
        })
    }

    private fun setPayAlbumAdapter() {
        val gridLayoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        payAlbumView.layoutManager = gridLayoutManager
        payAlbumAdapter = HomePayAlbumListAdapter()
        payAlbumView.setAdapter(payAlbumAdapter)
    }


    private fun setColumnAdpater() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        columnView.layoutManager = linearLayoutManager
        columnAdapter = HomeColumnAdapter()
        columnView.adapter = columnAdapter
        columnView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = 30
            }
        })
    }

    private fun setPrivateTeachAdapter() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        privateTeachView.layoutManager = linearLayoutManager
        privateTeachAdapter = HomePrivateTeachAdapter()
        privateTeachView.adapter = privateTeachAdapter
        privateTeachView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = 30
            }
        })
    }

    private fun setSortAdapter() {
        sortTab.setupWithViewPager(sortView)
        skillSortAdpater = HomeSkillSortPagerAdapter(childFragmentManager)
        sortView.adapter = skillSortAdpater
    }

    /**
     * 加载数据
     */
    override fun loadData() {
        val gs = Gson()
        val listBannerData = SaveSharePreferences.getListData("bannerData")
        val listStarData = SaveSharePreferences.getListData("starData")
        val listPayAlbumData = SaveSharePreferences.getListData("payAlbumData")

        if (listBannerData == null) {
            presenter?.getLooperData()
            //加载banner图数据
        } else {
//            refreshLayout.autoRefresh()
            val bannerJsonData = gs.fromJson<List<BannerData>>(
                listBannerData,
                object : TypeToken<List<BannerData>>() {}.type
            )
            setLooperAdapterData(bannerJsonData)
        }

        if (listStarData == null) {
            presenter?.getStarData()
            //加载球星数据
        } else {
            val starJsonData = gs.fromJson<List<StarData>>(
                listStarData,
                object : TypeToken<List<StarData>>() {}.type
            )
            setStarAdapterData(starJsonData)
        }

        if (listPayAlbumData == null) {
            //加载精品专辑列表数据
            presenter?.getPayAlbumData()
        } else {
            val payAlbumData = gs.fromJson<List<PayAlbumData>>(listPayAlbumData,
                object : TypeToken<List<PayAlbumData>>() {}.type
            )
            setPayAlbumAdapterData(payAlbumData)
        }

        //加载数据分类标签
        presenter?.getSkillSort()
        //加载私人训练数据
        presenter?.getPrivateTeach()
    }

    /**
     * 获取首页banner图数据
     */
    override fun onLooperDataLoad(data: List<BannerData>) {
        SaveSharePreferences.saveListData("bannerData", data)
        setLooperAdapterData(data)
    }

    private fun setLooperAdapterData(data: List<BannerData>) {
        mData.clear()
        mData.addAll(data)
        looperTitle.text = data[0].title
        looperAdapter?.addData(data)
    }

    /**
     * 获取球星数据
     */
    override fun onStarDataLoad(data: List<StarData>) {
        SaveSharePreferences.saveListData("starData", data)
        setStarAdapterData(data)
    }

    private fun setStarAdapterData(data: List<StarData>) {
        starAdapter?.addData(data)
    }

    /**
     * 获取精品专辑列表数据
     */
    override fun onPayAlbumDataLoad(data: List<PayAlbumData>) {
        SaveSharePreferences.saveListData("payAlbumData", data)
        setPayAlbumAdapterData(data)
    }

    private fun setPayAlbumAdapterData(data: List<PayAlbumData>) {
        payAlbumAdapter?.addData(data)
        columnAdapter?.addData(data)
    }

    /**
     * 获取技术分类标签列表
     */
    override fun onSkillSortDataLoad(data: List<SkillSortData>) {
        skillSortAdpater?.addData(data)
    }

    /**
     * 获取私人训练数据
     */
    override fun onPrivateTeachDataLoad(data: List<PrivateTeachData>) {
        privateTeachAdapter?.addData(data)
    }

    /**
     * 销毁
     */
    override fun release() {
        presenter?.unregisterViewCallback(this)

        looperAdapter?.cleanData()
        looperAdapter = null
        starAdapter?.cleanData()
        starAdapter = null
        payAlbumAdapter?.cleanData()
        payAlbumAdapter = null
        columnAdapter?.cleanData()
        columnAdapter = null
        skillSortAdpater?.cleanData()
        skillSortAdpater = null
        privateTeachAdapter?.cleanData()
        privateTeachAdapter = null

    }
}