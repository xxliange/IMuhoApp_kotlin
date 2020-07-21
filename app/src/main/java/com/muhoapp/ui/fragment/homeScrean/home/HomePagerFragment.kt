package com.muhoapp.ui.fragment.homeScrean.home

import android.graphics.Rect
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.home.BannerData
import com.muhoapp.model.domin.home.PayAlbumData
import com.muhoapp.model.domin.home.StarData
import com.muhoapp.presenter.impl.home.HomePagerPresenterImpl
import com.muhoapp.ui.adapter.home.HomeColumnAdapter
import com.muhoapp.ui.adapter.home.HomeLooperPagerAdapter
import com.muhoapp.ui.adapter.home.HomePayAlbumListAdapter
import com.muhoapp.ui.adapter.home.HomeStarListAdapter
import com.muhoapp.utils.PresenterManager
import com.muhoapp.view.home.IHomePagerCallback

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

    /**
     * 初始化view
     */
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)

        setLooperAdapter()
        setStarAdapter()
        setPayAlbumAdapter()
        setColumnAdpater()
    }

    fun setLooperAdapter() {
        looperAdapter = HomeLooperPagerAdapter()
        looper.adapter = looperAdapter
    }

    fun setStarAdapter() {
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

    fun setPayAlbumAdapter() {
        val gridLayoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        payAlbumView.layoutManager = gridLayoutManager
        payAlbumAdapter = HomePayAlbumListAdapter()
        payAlbumView.setAdapter(payAlbumAdapter)
    }


    fun setColumnAdpater(){
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
     * 销毁
     */
    override fun release() {
        presenter?.unregisterViewCallback(this)
        looperAdapter?.cleanData()
        looperAdapter = null
    }
}