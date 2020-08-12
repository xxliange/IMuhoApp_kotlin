package com.muhoapp.ui.fragment.video

import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseActivity
import com.muhoapp.model.domin.video.PayAlbumVideoData
import com.muhoapp.ui.views.CustomVideoActivity
import com.muhoapp.utils.CacheUtils
import com.muhoapp.utils.Utils
import com.muhoapp.view.utils.LogUtils
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import java.util.*

class PayAlbumVideoActivity : BaseActivity<Any>(), PayAlbumVideoFragment.PayAlbumVideoListener {

    private var myVideo: CustomVideoActivity? = null

    @BindView(R.id.activity_payALbum_head)
    lateinit var head: View
    @BindView(R.id.activity_payAlbum_noVip_modal)
    lateinit var noVipModal : ConstraintLayout
    @BindView(R.id.activity_payAlbum_back_btn)
    lateinit var backBtn : ImageView
    @BindView(R.id.activity_payAlbum_title)
    lateinit var title : TextView
    private var mSid = 0


    override fun getSubPresenter(): Any? {
        return null
    }

    override fun getContentView(): Int {
        return R.layout.activity_payalbum_video
    }

    override fun initListener() {
        backBtn.setOnClickListener{
            finish()
        }
    }

    override fun initView() {
        setTransparentStatusBar(false)
        val bundle = this.intent.extras
        mSid = bundle?.getInt("sid") as Int
        myVideo = findViewById(R.id.activity_payAlbum_video)

        head.layoutParams.height = Utils.getStatusBarHeight(this)
        head.requestLayout()

        noVipModal.layoutParams.height = Utils.getPixels(this, "width") * 9 / 16
        myVideo?.layoutParams?.height = Utils.getPixels(this, "width") * 9 / 16
        myVideo?.requestLayout()
        noVipModal.requestLayout()
    }

    override fun addFragment() {
        supportFragmentManager.beginTransaction().add(R.id.activity_payAlbum_fragment,PayAlbumVideoFragment.newInstance(mSid)).commit()
    }

    private fun initVideo(url: String, title: String) {
        val header: MutableMap<String, String> =
            HashMap()
        header["ee"] = "33"
        header["allowCrossProtocolRedirects"] = "true"
        val gsyVideoOptionBuilder = GSYVideoOptionBuilder()
        gsyVideoOptionBuilder
            .setVideoTitle(title)
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
        myVideo?.startPlayLogic()
    }

    private fun getCurPlay() : GSYVideoPlayer {
        return if (myVideo!!.fullWindowPlayer != null) {
            myVideo!!.fullWindowPlayer
        } else myVideo!!
    }

    override fun showItem(data: PayAlbumVideoData) {
        if (data.isfree == 0){
            getCurPlay().release()
            myVideo?.visibility = View.GONE
            noVipModal.visibility = View.VISIBLE
            title.text = data.title
        }else{
            initVideo(data.video, data.title)
            myVideo?.visibility = View.VISIBLE
            noVipModal.visibility = View.GONE
        }
    }

    override fun release() {
        getCurPlay().release()
    }
}