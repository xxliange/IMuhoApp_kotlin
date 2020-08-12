package com.muhoapp.ui.fragment.video

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.video.HomeUpdateVideoData
import com.muhoapp.model.domin.video.HomeUpdateVideoMoreData
import com.muhoapp.presenter.impl.video.HomeUpdateVideoPresenterImpl
import com.muhoapp.ui.adapter.video.HomeUpdateVideoMoreAdapter
import com.muhoapp.ui.adapter.video.VideoTagViewAdapter
import com.muhoapp.utils.PresenterManager
import com.muhoapp.view.utils.LogUtils
import com.muhoapp.view.video.HomeUpdateVideoCallback
import java.text.MessageFormat

class HomeUpDateVideoFragment : BaseFragment<HomeUpdateVideoPresenterImpl>(),
    HomeUpdateVideoCallback {
    @BindView(R.id.home_update_video_btn)
    lateinit var btn: TextView

    @BindView(R.id.home_update_video_title)
    lateinit var title: TextView

    @BindView(R.id.home_update_video_info)
    lateinit var info: TextView

    @BindView(R.id.home_update_video_tag_view)
    lateinit var tagView: RecyclerView

    @BindView(R.id.home_update_video_moreView)
    lateinit var moreView: RecyclerView

    @BindView(R.id.home_update_video_collectNum)
    lateinit var collectNum: TextView
    private var listen: HomeUpDateVideoListener? = null

    interface HomeUpDateVideoListener {
        fun showItem(data: HomeUpdateVideoData)
    }

    companion object {
        private const val CID = "cid"
        fun newInstance(cid: Int): HomeUpDateVideoFragment {
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            val fragment = HomeUpDateVideoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is HomeUpDateVideoListener){
            listen = context
        }
    }

    override fun getSubPresenter(): HomeUpdateVideoPresenterImpl? {
        return PresenterManager.getHomeUpdateVideoPresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_home_update_video
    }

    private var videoTagAdapter: VideoTagViewAdapter? = null
    private var videoMoreAdapter: HomeUpdateVideoMoreAdapter? = null
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)
        videoTagAdapter = VideoTagViewAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        tagView.layoutManager = linearLayoutManager
        tagView.adapter = videoTagAdapter

        videoMoreAdapter = HomeUpdateVideoMoreAdapter()
        moreView.layoutManager = LinearLayoutManager(context)
        moreView.adapter = videoMoreAdapter
    }

    override fun bindEvent() {
        presenter?.registerViewCallback(this)
    }

    override fun loadData() {
        val cid = arguments?.getInt(CID)
        if (cid != 0) {
            presenter?.getVideoInfo(cid!!)
        }
        presenter?.getVideoMore()
    }

    override fun onLoadVideoInfo(data: HomeUpdateVideoData) {
        bindData(data)
    }

    override fun onLoadMoreVideo(data: List<HomeUpdateVideoMoreData>) {
        videoMoreAdapter?.addData(data)
    }

    private fun bindData(data: HomeUpdateVideoData) {
        if (data.pagetext == ""){
            btn.visibility = View.GONE
        }else{
            btn.visibility = View.VISIBLE
            btn.text = data.pagetext
        }
        title.text = data.title
        collectNum.text = data.collection.toString()
        info.text = MessageFormat.format("{0}次播放,${data.update_time}发布", data.view)
        videoTagAdapter?.addData(data.tags.split(","))
        listen?.showItem(data)
    }

    override fun release() {
        presenter?.unregisterViewCallback(this)
        videoTagAdapter?.clearData()
        videoMoreAdapter?.clearData()
        videoTagAdapter = null
        videoMoreAdapter = null
        listen = null

    }
}