package com.muhoapp.ui.activity.video

import android.content.Intent
import android.view.View
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseActivity
import com.muhoapp.model.domin.video.HomeUpdateVideoData
import com.muhoapp.ui.fragment.video.HomeUpDateVideoFragment
import com.muhoapp.ui.views.CustomVideoActivity
import com.muhoapp.utils.Utils
import com.muhoapp.view.utils.LogUtils
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import java.util.*

class HomeUpDateVideoActivity : BaseActivity<Any>() , HomeUpDateVideoFragment.HomeUpDateVideoListener {

    @BindView(R.id.activity_updateVideo_head)
    lateinit var head: View

    private var mCid = 0
    private var myVideo : CustomVideoActivity? = null

//    @BindView(R.id.activity_updateVideo_video)
//    lateinit var myVideo: StandardGSYVideoPlayer

    override fun getSubPresenter(): Any? {
        return null
    }

    override fun getContentView(): Int {
        return R.layout.activity_home_update_video
    }

    override fun initView() {
        val bundle = this.intent.extras
        val cid = bundle?.get("cid") as Int
        mCid = cid
        myVideo = findViewById(R.id.activity_updateVideo_video)
        setTransparentStatusBar(false)
        head.layoutParams.height = Utils.getStatusBarHeight(this)
        head.requestLayout()

        myVideo?.layoutParams?.height = Utils.getPixels(this, "width")*9/16
        myVideo?.requestLayout()

        supportFragmentManager.beginTransaction().add(R.id.activity_upDateVideo_fragment, HomeUpDateVideoFragment.newInstance(mCid)).commit()
    }

    private fun initVideo(data: HomeUpdateVideoData) {
        val url = "https://m.muho.tv/bXVob1RW_1587445507.mp4"
        val header: MutableMap<String, String> =
            HashMap()
        header["ee"] = "33"
        header["allowCrossProtocolRedirects"] = "true"
        val gsyVideoOptionBuilder = GSYVideoOptionBuilder()
        gsyVideoOptionBuilder
            .setVideoTitle(data.title)
            .setShowDragProgressTextOnSeekBar(true)
            .setCacheWithPlay(true)
            .setRotateViewAuto(true)
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setAutoFullWithSize(false)
            .setShowFullAnimation(false)
            .setNeedLockFull(false)
            .setUrl(data.video)
            .setMapHeadData(header)
            .setCacheWithPlay(false)
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String, vararg objects: Any) {
                    super.onPrepared(url, *objects)
                }

                override fun onQuitFullscreen(
                    url: String,
                    vararg objects: Any
                ) {
                    super.onQuitFullscreen(url, *objects)
                }
            }).build(myVideo)

        myVideo?.startPlayLogic()
    }

    private fun getCurPlay() : GSYVideoPlayer{
        return if (myVideo!!.fullWindowPlayer != null) {
            myVideo!!.fullWindowPlayer
        } else myVideo!!
    }

    override fun release() {
        getCurPlay().release()
    }

    override fun showItem(data: HomeUpdateVideoData) {
        initVideo(data)
    }

}