package com.muhoapp.ui.fragment.video

import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.ui.adapter.video.TeachVideoContentCountAdapter
import com.muhoapp.utils.Utils
import com.muhoapp.view.utils.LogUtils
import java.util.ArrayList

class TeachVideoCountListFragment : BaseFragment<Any>() {
    @BindView(R.id.teach_video_count_view)
    lateinit var countView : RecyclerView
    private var windowManager: WindowManager? = null
    override fun getSubPresenter(): Any? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_teach_video_count_list
    }

    companion object{
        private const val TEACH_COUNT_LIST = "mData"
        private const val TEACH_CID = "cid"
        fun newInstance(
            mData: ArrayList<TeachVideoListData>,
            cid: Int
        ): TeachVideoCountListFragment{
            val bundle = Bundle()
            val dataJson = Gson().toJson(mData)
            bundle.putString(TEACH_COUNT_LIST, dataJson)
            bundle.putInt(TEACH_CID,cid)
            val fragment = TeachVideoCountListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var teachVideoContentCountAdapter : TeachVideoContentCountAdapter? = null
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)

        teachVideoContentCountAdapter = TeachVideoContentCountAdapter()
        countView.adapter = teachVideoContentCountAdapter
        countView.layoutManager =
                    GridLayoutManager(context, 4, RecyclerView.VERTICAL, false)
        countView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.bottom = Utils.dp2px(context!!, 10F).toInt()
                windowManager = activity?.windowManager
                val displayMetrics = DisplayMetrics()
                windowManager?.defaultDisplay?.getMetrics(displayMetrics)
                val widthPixels = displayMetrics.widthPixels

                view.layoutParams.width = (widthPixels / 4) - 40
                view.requestLayout()
            }
        })
        val mDataString = arguments?.getString(TEACH_COUNT_LIST)
        val mData = Gson().fromJson<List<TeachVideoListData>>(mDataString, object : TypeToken<List<TeachVideoListData>>(){}.type)
        teachVideoContentCountAdapter?.addData(mData)
        teachVideoContentCountAdapter?.setCid(arguments?.getInt(TEACH_CID))
    }

    override fun release() {
        teachVideoContentCountAdapter = null
    }

    fun upDateArguments(cid: Int) {
        arguments?.putInt(TEACH_CID,cid)
    }
}