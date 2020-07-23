package com.muhoapp.ui.fragment.homeScrean.home

import android.graphics.Rect
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.home.*
import com.muhoapp.presenter.impl.home.HomePagerPresenterImpl
import com.muhoapp.ui.adapter.home.*
import com.muhoapp.utils.PresenterManager
import com.muhoapp.view.home.IHomePagerCallback
import com.muhoapp.view.utils.LogUtils

class HomePagerFragment : BaseFragment<HomePagerPresenterImpl>(), IHomePagerCallback {

    private val TAG = "HomePagerFragment"
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
    lateinit var columnView : RecyclerView

    @BindView(R.id.home_private_teach_view)
    lateinit var privateTeachView : RecyclerView

    @BindView(R.id.home_sort_tab)
    lateinit var sortTab : TabLayout

    @BindView(R.id.home_sort_view)
    lateinit var sortView : ViewPager

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
    }

    private var looperAdapter: HomeLooperPagerAdapter? = null
    private var starAdapter: HomeStarListAdapter? = null
    private var payAlbumAdapter: HomePayAlbumListAdapter? = null
    private var columnAdapter : HomeColumnAdapter? = null
    private var skillSortAdpater : HomeSkillSortPagerAdapter? = null
    private var privateTeachAdapter : HomePrivateTeachAdapter? = null

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


    private fun setColumnAdpater(){
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        columnView.layoutManager = linearLayoutManager
        columnAdapter = HomeColumnAdapter()
        columnView.adapter = columnAdapter
        columnView.addItemDecoration(object : RecyclerView.ItemDecoration(){
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

    private fun setPrivateTeachAdapter(){
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        privateTeachView.layoutManager = linearLayoutManager
        privateTeachAdapter = HomePrivateTeachAdapter()
        privateTeachView.adapter = privateTeachAdapter
        privateTeachView.addItemDecoration(object : RecyclerView.ItemDecoration(){
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

    private fun setSortAdapter(){
        sortTab.setupWithViewPager(sortView)
        skillSortAdpater = HomeSkillSortPagerAdapter(childFragmentManager)
        sortView.adapter = skillSortAdpater
    }

    /**
     * 加载数据
     */
    override fun loadData() {
        //加载banner图数据
        presenter?.getLooperData()
        //加载球星数据
        presenter?.getStarData()
        //加载精品专辑列表数据
        presenter?.getPayAlbumData()
        //加载数据分类标签
        presenter?.getSkillSort()
        //加载私人训练数据
        presenter?.getPrivateTeach()
    }

    /**
     * 获取首页banner图数据
     */
    override fun onLooperDataLoad(data: List<BannerData>) {
        mData.clear()
        mData.addAll(data)
        looperTitle.text = data.get(0).title
        looperAdapter?.addData(data)
    }

    /**
     * 获取球星数据
     */
    override fun onStarDataLoad(data: List<StarData>) {
        starAdapter?.addData(data)
    }

    /**
     * 获取精品专辑列表数据
     */
    override fun onPayAlbumDataLoad(data: List<PayAlbumData>) {
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