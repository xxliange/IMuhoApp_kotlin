package com.muhoapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.muhoapp.R
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

class CustomVideoActivity : StandardGSYVideoPlayer {
    override fun init(context: Context?) {
        super.init(context)
    }
    constructor(context: Context?, fullFlag: Boolean?) : super(
        context,
        fullFlag
    )

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    )

    override fun getLayoutId(): Int {
        return R.layout.activity_custom_video
    }

}
