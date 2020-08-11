package com.muhoapp.ui.fragment.video

import android.graphics.Rect
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.model.domin.video.TeachVideoMoreRandom
import com.muhoapp.model.domin.video.VideoCollectInfo
import com.muhoapp.presenter.impl.video.TeachVideoPresenterImpl
import com.muhoapp.ui.adapter.video.TeachVideoContentCountViewPagerAdapter
import com.muhoapp.ui.adapter.video.TeachVideoContentListAdapter
import com.muhoapp.ui.adapter.video.TeachVideoMoreRandomAdapter
import com.muhoapp.utils.PresenterManager
import com.muhoapp.view.teach.ITeachVideoCallback
import java.text.MessageFormat

/**
 * 普通教学视频fragment
 */

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

    @BindView(R.id.teach_video_content_count_view)
    lateinit var countView: ViewPager

    @BindView(R.id.teach_Video_content_count_tab)
    lateinit var countTab: TabLayout

    @BindView(R.id.teach_video_content_count_closebtn)
    lateinit var countCloseBtn: ImageView

    @BindView(R.id.teach_video_content_count_container)
    lateinit var countContainer: LinearLayout

    @BindView(R.id.teach_video_content_collect_num)
    lateinit var collectNum: TextView

    @BindView(R.id.teach_video_content_collect_img)
    lateinit var collectImg: ImageView

    @BindView(R.id.teach_video_content_collect_btn)
    lateinit var collectBtn: LinearLayout

    private var mCid = 0
    private var mCollectNum = 0
    private var mTeachVideoInfo: TeachVideoListData? = null

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
    private var teachVideoContentCountViewPageAdapter: TeachVideoContentCountViewPagerAdapter? =
        null


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
        teachVideoContentCountViewPageAdapter =
            TeachVideoContentCountViewPagerAdapter(childFragmentManager)
        countView.adapter = teachVideoContentCountViewPageAdapter
        teachVideoContentCountViewPageAdapter?.addData(countTabList)
    }

    override fun loadData() {
        presenter?.getTeachVideoListData()
        presenter?.getTeachVideoMoreRandom()
//        presenter?.getViewVideo()
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
                mTeachVideoInfo = data
                teachVideoContentAdapter?.choseItem(pos)
                setVideoInfo(data, null)
                mCid = data.cid
                presenter?.getVideoCollect(mCid)
            }
        })

        collectBtn.setOnClickListener {
            val cid = mTeachVideoInfo?.cid
            presenter?.getVideoUserCollect(cid!!, 2, 3)
        }

    }

    /**
     * 获取视频列表数据
     */
    override fun onLoadTeachVideoList(data: List<TeachVideoListData>) {
        setVideoInfo(data[0], data.size)
        listMoreBtn.text = MessageFormat.format("全{0}集,查看详情", data.size)
        teachVideoContentAdapter?.addData(data)
        teachVideoContentCountViewPageAdapter?.addListData(data)
        presenter?.getVideoCollect(data[0].cid)
        mTeachVideoInfo = data[0]
//        teachVideoContentCountAdapter?.addData(data)
    }

    /**
     * 获取更多推荐
     */
    override fun onLoadTeachMoreRandom(data: List<TeachVideoMoreRandom>) {
        teachVideoMoreRandomAdapter?.addData(data)
    }

    override fun onLoadViewVideoSuccess() {
    }

    /**
     * 获取视频收藏数和用户收藏状态
     */
    override fun onLoadVideoCollect(data: VideoCollectInfo) {
        mCollectNum = data.collectNum
        collectNum.text = mCollectNum.toString()
        collectImg.isSelected = data.isCollect
        if (data.isCollect) {
            collectNum.setTextColor(resources.getColor(R.color.mainColor, null))
        } else {
            collectNum.setTextColor(resources.getColor(R.color.colorTextLow, null))
        }
    }

    override fun onLoadVideoUserCollect(msg: String) {
        if (msg == "取消收藏成功") {
            collectImg.isSelected = false
            mCollectNum -= 1
            collectNum.setTextColor(resources.getColor(R.color.colorTextLow, null))
        } else if (msg == "收藏成功") {
            collectImg.isSelected = true
            mCollectNum += 1
            collectNum.setTextColor(resources.getColor(R.color.mainColor, null))
        }
        collectNum.text = mCollectNum.toString()
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
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