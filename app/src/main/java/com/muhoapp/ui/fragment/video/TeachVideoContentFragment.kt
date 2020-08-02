package com.muhoapp.ui.fragment.video

import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginRight
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.model.domin.video.TeachVideoMoreRandom
import com.muhoapp.presenter.impl.video.TeachVideoPresenterImpl
import com.muhoapp.ui.adapter.video.TeachVideoContentCountAdapter
import com.muhoapp.ui.adapter.video.TeachVideoContentCountViewPagerAdapter
import com.muhoapp.ui.adapter.video.TeachVideoContentListAdapter
import com.muhoapp.ui.adapter.video.TeachVideoMoreRandomAdapter
import com.muhoapp.utils.PresenterManager
import com.muhoapp.utils.Utils
import com.muhoapp.view.teach.ITeachVideoCallback
import com.muhoapp.view.utils.LogUtils
import okhttp3.internal.Util
import java.text.MessageFormat

class TeachVideoContentFragment : BaseFragment<TeachVideoPresenterImpl>(), ITeachVideoCallback {

    private var countTabList = ArrayList<Int>()

    @BindView(R.id.teach_video_content_title)
    lateinit var title: TextView

    @BindView(R.id.teach_video_content_count)
    lateinit var count: TextView

    @BindView(R.id.teach_video_content_views)
    lateinit var views: TextView

    @BindView(R.id.teach_video_content_list_more)
    lateinit var listMoreBtn: TextView

    @BindView(R.id.teach_video_content_list)
    lateinit var listView: RecyclerView

    @BindView(R.id.teach_video_content_random_view)
    lateinit var randomView: RecyclerView

    private var mCid = 0

//    @BindView(R.id.teach_video_content_countList)
//    lateinit var countView: RecyclerView

    @BindView(R.id.teach_video_content_count_view)
    lateinit var countView : ViewPager

    @BindView(R.id.teach_Video_content_count_tab)
    lateinit var countTab : TabLayout

    @BindView(R.id.teach_video_content_count_closebtn)
    lateinit var countCloseBtn: ImageView

    @BindView(R.id.teach_video_content_count_container)
    lateinit var countContainer: LinearLayout

    private var windowManager: WindowManager? = null
    override fun getSubPresenter(): TeachVideoPresenterImpl? {
        return PresenterManager.getTeachVideoPresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.activity_teach_video_content
    }

    private var teachVideoContentAdapter: TeachVideoContentListAdapter? = null
    private var teachVideoMoreRandomAdapter: TeachVideoMoreRandomAdapter? = null
//    private var teachVideoContentCountAdapter: TeachVideoContentCountAdapter? = null
    private var teachVideoContentCountViewPageAdapter : TeachVideoContentCountViewPagerAdapter? = null


    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)

        countTabList.add(0)
        countTabList.add(1)

        teachVideoContentAdapter = TeachVideoContentListAdapter()
        listView.adapter = teachVideoContentAdapter
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        listView.layoutManager = linearLayout
        listView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = 30
            }
        })

        teachVideoMoreRandomAdapter = TeachVideoMoreRandomAdapter()
        randomView.adapter = teachVideoMoreRandomAdapter
        randomView.layoutManager = LinearLayoutManager(context)

        countTab.setupWithViewPager(countView)
        teachVideoContentCountViewPageAdapter = TeachVideoContentCountViewPagerAdapter(childFragmentManager)
        countView.adapter = teachVideoContentCountViewPageAdapter
        teachVideoContentCountViewPageAdapter?.addData(countTabList)

//        teachVideoContentCountAdapter = TeachVideoContentCountAdapter()
//        countView.adapter = teachVideoContentCountAdapter
//        countView.layoutManager = GridLayoutManager(context, 4, RecyclerView.VERTICAL, false)
//        countView.addItemDecoration(object : RecyclerView.ItemDecoration() {
//            override fun getItemOffsets(
//                outRect: Rect,
//                view: View,
//                parent: RecyclerView,
//                state: RecyclerView.State
//            ) {
//                outRect.bottom = Utils.dp2px(context!!, 10F).toInt()
//                windowManager = activity?.windowManager
//                val displayMetrics = DisplayMetrics()
//                windowManager?.defaultDisplay?.getMetrics(displayMetrics)
//                val widthPixels = displayMetrics.widthPixels
//
//                LogUtils.d(this, "widthPixels --> ${widthPixels / 4}")
//                LogUtils.d(this, "width --> ${Utils.dp2px(context!!, (widthPixels / 4).toFloat())}")
//                view.layoutParams.width = (widthPixels / 4) - 40
//                view.requestLayout()
//            }
//        })
    }

    override fun loadData() {
        presenter?.getTeachVideoListData()
        presenter?.getTeachVideoMoreRandom()
    }

    override fun bindEvent() {
        presenter?.registerViewCallback(this)

        countCloseBtn.setOnClickListener {
            countContainer.visibility = View.GONE
        }
        listMoreBtn.setOnClickListener {
            countContainer.visibility = View.VISIBLE
            teachVideoContentCountViewPageAdapter?.setCid(mCid)

        }
        teachVideoContentAdapter?.setOnItemClickListener(object :
            TeachVideoContentListAdapter.OnItemClickListener {
            override fun onItemClick(data: TeachVideoListData, pos: Int) {
                teachVideoContentAdapter?.choseItem(pos)
                setVideoInfo(data, null)
                mCid = data.cid
            }

        })

    }

    /**
     * 获取视频列表数据
     */
    override fun onLoadTeachVideoList(data: List<TeachVideoListData>) {
        setVideoInfo(data[0], data.size)
        listMoreBtn.text = MessageFormat.format("全{0}集,查看详情", data.size)
        teachVideoContentAdapter?.addData(data)
        teachVideoContentCountViewPageAdapter?.addListData(data)
//        teachVideoContentCountAdapter?.addData(data)
    }

    /**
     * 获取更多推荐
     */
    override fun onLoadTeachMoreRandom(data: List<TeachVideoMoreRandom>) {
        teachVideoMoreRandomAdapter?.addData(data)
    }

    private fun setVideoInfo(data: TeachVideoListData, size: Int?) {
        if (size !== null) {
            count.text = MessageFormat.format("${data.name},已完结,全{0}集", size)
        }
        title.text = data.title
        views.text = MessageFormat.format("{0}播放", data.view)
    }

    /**
     * 销毁 垃圾回收
     */
    override fun release() {
        presenter?.unregisterViewCallback(this)
        teachVideoContentAdapter?.cleanData()
        teachVideoContentAdapter = null
        teachVideoMoreRandomAdapter?.cleanData()
        teachVideoMoreRandomAdapter = null
    }
}