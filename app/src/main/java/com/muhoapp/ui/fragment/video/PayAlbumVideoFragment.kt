package com.muhoapp.ui.fragment.video

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.video.PayAlbumInfoData
import com.muhoapp.model.domin.video.PayAlbumInfoMoreData
import com.muhoapp.model.domin.video.PayAlbumVideoCollectData
import com.muhoapp.model.domin.video.PayAlbumVideoData
import com.muhoapp.presenter.impl.video.PayAlbumPresenterImpl
import com.muhoapp.ui.adapter.video.PayAlbumListAdapter
import com.muhoapp.ui.adapter.video.PayAlbumMoreAdapter
import com.muhoapp.ui.adapter.video.VideoTagViewAdapter
import com.muhoapp.ui.views.CenterLayoutManager
import com.muhoapp.utils.PresenterManager
import com.muhoapp.view.utils.LogUtils
import com.muhoapp.view.video.PayAlbumVideoCallback
import java.text.MessageFormat

class PayAlbumVideoFragment : BaseFragment<PayAlbumPresenterImpl>(), PayAlbumVideoCallback {
    @BindView(R.id.payAlbum_name)
    lateinit var name: TextView

    @BindView(R.id.payAlbum_coach_avatar)
    lateinit var coachAvatar: ImageView

    @BindView(R.id.payAlbum_coach_name)
    lateinit var coachName: TextView

    @BindView(R.id.payAlbum_coach_info)
    lateinit var coachInfo: TextView

    @BindView(R.id.payAlbum_count)
    lateinit var counts: TextView

    @BindView(R.id.payAlbum_title)
    lateinit var title: TextView

    @BindView(R.id.payAlbum_tag_view)
    lateinit var tagsView: RecyclerView

    @BindView(R.id.payAlbum_views_info)
    lateinit var viewsInfo: TextView

    @BindView(R.id.merge_video_user_control_collectNum)
    lateinit var collectNum: TextView

    @BindView(R.id.merge_video_user_control_collectImg)
    lateinit var collectImg: ImageView

    @BindView(R.id.payAlbum_video_content_list)
    lateinit var videoList: RecyclerView

    @BindView(R.id.payAlbum_video_more_list)
    lateinit var moreList: RecyclerView

    private var videoListManager : CenterLayoutManager? = null
    private var listener : PayAlbumVideoListener? = null

    interface PayAlbumVideoListener{
        fun showItem(data : PayAlbumVideoData)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PayAlbumVideoListener){
            listener = context
        }
    }

    companion object {
        private const val ID = "id"
        fun newInstance(id: Int): PayAlbumVideoFragment {
            val bundle = Bundle()
            bundle.putInt(ID, id)
            val fragment = PayAlbumVideoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getSubPresenter(): PayAlbumPresenterImpl {
        return PresenterManager.getPayAlbumPresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_payalbum_video
    }

    private var tagsAdapter: VideoTagViewAdapter? = null
    private var listAdapter: PayAlbumListAdapter? = null
    private var moreAdapter: PayAlbumMoreAdapter? = null
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)
        tagsAdapter = VideoTagViewAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        tagsView.layoutManager = linearLayoutManager
        tagsView.adapter = tagsAdapter

        listAdapter = PayAlbumListAdapter()
        videoList.adapter = listAdapter
        videoListManager = CenterLayoutManager(context)
        videoListManager?.orientation = LinearLayoutManager.HORIZONTAL
        videoList.layoutManager = videoListManager
        videoList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = 30
            }
        })

        moreAdapter = PayAlbumMoreAdapter()
        moreList.adapter = moreAdapter
        moreList.layoutManager = LinearLayoutManager(context)

    }

    override fun loadData() {
        val id = arguments?.getInt(ID)
        presenter?.getVideoList(id!!)
        presenter?.getVideoInfo(id!!)
        presenter?.getVideoMoreData()
    }

    override fun bindEvent() {
        presenter?.registerViewCallback(this)
        listAdapter?.setOnItemClickListener(object : PayAlbumListAdapter.OnItemClickListener{
            override fun itemClick(data: PayAlbumVideoData, index: Int) {
                listAdapter?.getIndex(index)
                bindData(data)
                videoListManager?.smoothScrollToPosition(videoList,object : RecyclerView.State(){},index)
            }

        })
    }

    override fun onLoadVideoList(data: List<PayAlbumVideoData>) {
        if(data.size > 0){
            bindData(data[0])
            listAdapter?.addData(data)
            listAdapter?.getIndex(0)
        }
    }

    private fun bindData(data: PayAlbumVideoData) {
        title.text = data.title
        tagsAdapter?.addData(data.tags.split(","))
        viewsInfo.text = MessageFormat.format("{0}次播放", data.view)
        presenter?.getVideoCollect(data.cid)
        listener?.showItem(data)
    }


    override fun onLoadVideoInfo(data: PayAlbumInfoData) {
        bindCoachData(data)
    }

    private fun bindCoachData(data: PayAlbumInfoData) {
        name.text = data.name
        coachName.text = data.albumName
        coachInfo.text = data.coachIntro
        counts.text = MessageFormat.format("全{0}集", data.cons)
        Glide
            .with(this)
            .load(data.coachThumb)
            .placeholder(R.drawable.placeholder)
            .apply(
                RequestOptions.bitmapTransform(
                    CircleCrop()
                )
            ).into(coachAvatar)

    }

    override fun onLoadMoreVideo(data: List<PayAlbumInfoMoreData>) {
        moreAdapter?.addData(data)
    }

    override fun onLoadCollectInfo(data: PayAlbumVideoCollectData) {
        collectNum.text = data.collectNum.toString()
        if (data.isCollect) {
            collectNum.setTextColor(resources.getColor(R.color.mainColor, null))
        } else {
            collectNum.setTextColor(resources.getColor(R.color.colorTextLow, null))
        }
        collectImg.isSelected = data.isCollect

    }

    override fun release() {
        presenter?.unregisterViewCallback(this)
        listener = null
    }
}