package com.muhoapp.ui.fragment.video

import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseActivity
import com.muhoapp.utils.Utils
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import java.util.HashMap

class TeachVideoActivity : BaseActivity<Any>() {
    @BindView(R.id.activity_teachVideo_video)
    lateinit var myVideo: StandardGSYVideoPlayer

    @BindView(R.id.activity_teachVideo_head)
    lateinit var head: View

    override fun getSubPresenter(): Any? {
        return null
    }

    override fun getContentView(): Int {
        return R.layout.activity_teach_video
    }

    override fun initView() {
        setTransparentStatusBar(false)

        val statusBarHeight = Utils.getStatusBarHeight(this)
        head.layoutParams.height = statusBarHeight
        head.requestLayout()


        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val widthPixels = displayMetrics.widthPixels

        myVideo.layoutParams.height = widthPixels*9/16
        myVideo.requestLayout()

        supportFragmentManager.beginTransaction().add(R.id.activity_teachVideo_fragment,TeachVideoContentFragment()).commit()
        initVideo()
    }

    private fun initVideo(){
        val url = "https://m.muho.tv/bXVob1RW_1580917204.mp4"
        val header: MutableMap<String, String> =
            HashMap()
        header["ee"] = "33"
        header["allowCrossProtocolRedirects"] = "true"
        val gsyVideoOptionBuilder = GSYVideoOptionBuilder()
        gsyVideoOptionBuilder
            .setVideoTitle("幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球幕后篮球")
            .setShowDragProgressTextOnSeekBar(true)
            .setCacheWithPlay(true)
            .setRotateViewAuto(true)
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setAutoFullWithSize(false)
            .setShowFullAnimation(false)
            .setNeedLockFull(false)
            .setUrl(url)
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
//        myVideo.startPlayLogic()
    }
}