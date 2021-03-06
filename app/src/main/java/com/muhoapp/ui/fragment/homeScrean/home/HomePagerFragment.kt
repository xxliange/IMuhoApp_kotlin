package com.muhoapp.ui.fragment.homeScrean.home

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import android.view.animation.AlphaAnimation
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
import com.muhoapp.ui.fragment.video.PayAlbumVideoActivity
import com.muhoapp.utils.CacheUtils
import com.muhoapp.utils.PresenterManager
import com.muhoapp.utils.SaveSharePreferences
import com.muhoapp.utils.Utils
import com.muhoapp.view.home.IHomePagerCallback
import com.muhoapp.view.utils.LogUtils
import com.scwang.smart.refresh.layout.api.RefreshLayout

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

    @BindView(R.id.home_follow_view)
    lateinit var followView : RecyclerView

    @BindView(R.id.home_column_view)
    lateinit var columnView: RecyclerView

    @BindView(R.id.home_private_teach_view)
    lateinit var privateTeachView: RecyclerView

    @BindView(R.id.home_sort_tab)
    lateinit var sortTab: TabLayout

    @BindView(R.id.home_sort_view)
    lateinit var sortView: RecyclerView

    @BindView(R.id.home_del_cache)
    lateinit var btn: Button

    @BindView(R.id.refreshLayout)
    lateinit var refreshLayout: RefreshLayout

    @BindView(R.id.home_looper_container)
    lateinit var looperContainer: RelativeLayout

    @BindView(R.id.home_skillSort_btn)
    lateinit var skillSortBtn: TextView

    @BindView(R.id.home_skillSort_btn_container)
    lateinit var skillSortBtnContainer : LinearLayout

    private var windowManager: WindowManager? = null
    private var viewOldHeight: Int = 0
    private var va : ValueAnimator? = null
    private var sortContentData = ArrayList<SkillContentData>()

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
        btn.setOnClickListener {
            CacheUtils.deleteFileByDir(CacheUtils.CacheType.PREFERENCES, context)
        }
        sortTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val title = tab?.text
                val aa = AlphaAnimation(1.0F, 0F)
                aa.duration = 200
                aa.fillAfter = true
                sortView.startAnimation(aa)
                presenter?.getSkillSortContent(title.toString())
            }
        })
        refreshLayout.setOnRefreshListener{
            presenter?.getLooperData()
            presenter?.getStarData()
            presenter?.getPayAlbumData()
            //加载数据分类标签
            presenter?.getSkillSort()
            //加载私人训练数据
            presenter?.getPrivateTeach()
            it.finishRefresh()
        }
        payAlbumAdapter?.setOnItemClickListener(object : HomePayAlbumListAdapter.OnItemClickListener{
            override fun onItemClick(data: PayAlbumData) {
                val intent = Intent(context, PayAlbumVideoActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("sid",data.sid)
                intent.putExtras(bundle)
                startActivity(intent)
            }

        })
    }

    private var looperAdapter: HomeLooperPagerAdapter? = null
    private var starAdapter: HomeStarListAdapter? = null
    private var payAlbumAdapter: HomePayAlbumListAdapter? = null
    private var columnAdapter: HomeColumnAdapter? = null
    private var skillSortAdapter: HomeSkillSortPagerAdapter? = null
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

        windowManager = activity?.windowManager
        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val widthPixels = displayMetrics.widthPixels

        val marginLayoutParams = MarginLayoutParams(looperContainer.layoutParams)
        val layoutParams = LinearLayout.LayoutParams(marginLayoutParams)
        layoutParams.height = widthPixels / 2
        looperContainer.layoutParams = layoutParams

        setItemTitle(rootView)

    }

    /**
     * 设置每个块的标题
     */
    private fun setItemTitle(rootView: View){
        rootView.findViewById<LinearLayout>(R.id.home_star_title).findViewById<TextView>(R.id.include_home_item_text).text = "球星专区"
        rootView.findViewById<LinearLayout>(R.id.home_payAlbum_title).findViewById<TextView>(R.id.include_home_item_text).text = "精品教学"
        rootView.findViewById<LinearLayout>(R.id.home_private_teach_title).findViewById<TextView>(R.id.include_home_item_text).text = "私人训练"
        rootView.findViewById<LinearLayout>(R.id.home_sort_title).findViewById<TextView>(R.id.include_home_item_text).text = "技术分类"
        rootView.findViewById<LinearLayout>(R.id.home_follow_title).findViewById<TextView>(R.id.include_home_item_text).text = "幕后跟拍"
        rootView.findViewById<LinearLayout>(R.id.home_column_title).findViewById<TextView>(R.id.include_home_item_text).text = "推荐专栏"
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
        val gridLayoutManager1 = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        payAlbumView.layoutManager = gridLayoutManager
        followView.layoutManager = gridLayoutManager1
        payAlbumAdapter = HomePayAlbumListAdapter()
        followView.adapter = payAlbumAdapter
        payAlbumView.adapter = payAlbumAdapter
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
//                outRect.right = 10
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
//                outRect.right = 10
            }
        })
    }

    private fun setSortAdapter() {
        skillSortAdapter = HomeSkillSortPagerAdapter()
        sortView.adapter = skillSortAdapter
        sortView.layoutManager = LinearLayoutManager(context)
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
            val payAlbumData = gs.fromJson<List<PayAlbumData>>(
                listPayAlbumData,
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
        data.indices.forEach {
            sortTab.addTab(sortTab.newTab().setText(data[it].keyword))
        }
        presenter?.getSkillSortContent(data[0].keyword)
    }

    /**
     * 获取技术分类内容
     */
    override fun onSkillSortContentDataLoad(data: List<SkillContentData>) {
        sortContentData.clear()
        sortContentData.addAll(data)
        val aa = AlphaAnimation(0F, 1.0F)
        aa.duration = 200
        aa.fillAfter = true
        sortView.startAnimation(aa)
        /**
         * 动态修改高度
         */
        val itemHeight = 100
        val viewHeight: Int
        viewHeight = if (data.size < 4) {
            itemHeight * data.size
        } else {
            itemHeight * 4
        }
        val starHeight = sortView.computeVerticalScrollRange()
        val endHeight = Utils.dp2px(context!!, viewHeight.toFloat()).toInt()
        va = ValueAnimator.ofInt(starHeight,endHeight)
        va?.addUpdateListener {
            val animatedValue = it.animatedValue as Int
            sortView.layoutParams.height = animatedValue
            sortView.requestLayout()
        }
        va?.duration = 200
        va?.start()
        /**
         * 设置是否有查看更多按钮
         */
        if (data.size < 4) {
            skillSortBtnContainer.visibility = View.GONE
        } else {
            skillSortBtnContainer.visibility = View.VISIBLE
        }
        /**
         * 适配器添加数据
         */
        skillSortAdapter?.addData(data)
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
        skillSortAdapter?.cleanData()
        skillSortAdapter = null
        privateTeachAdapter?.cleanData()
        privateTeachAdapter = null
        va?.end()
    }
}