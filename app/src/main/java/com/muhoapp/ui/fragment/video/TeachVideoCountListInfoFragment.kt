package com.muhoapp.ui.fragment.video

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.video.TeachVideoListData
import com.muhoapp.ui.adapter.video.TeachVideoCountInfoAdapter
import java.util.ArrayList

class TeachVideoCountListInfoFragment : BaseFragment<Any>() {
    @BindView(R.id.teach_video_count_info_view)
    lateinit var countInfoView : RecyclerView
    override fun getSubPresenter(): Any? {
        return null
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_teach_video_count_list_info
    }
    companion object{
        private const val TEACH_COUNT_LIST = "mData"
        fun newInstance(mData: ArrayList<TeachVideoListData>): TeachVideoCountListInfoFragment{
            val bundle = Bundle()
            val dataJson = Gson().toJson(mData)
            bundle.putString(TEACH_COUNT_LIST, dataJson)
            val fragment = TeachVideoCountListInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var teachVideoCountInfoAdapter : TeachVideoCountInfoAdapter? = null
    override fun initView(rootView: View) {
        switchUiByState(PageState.SUCCESS)

        teachVideoCountInfoAdapter = TeachVideoCountInfoAdapter()
        countInfoView.adapter = teachVideoCountInfoAdapter
        countInfoView.layoutManager = LinearLayoutManager(context)

        val mDataString = arguments?.getString(TEACH_COUNT_LIST)
        val mData = Gson().fromJson<List<TeachVideoListData>>(mDataString, object : TypeToken<List<TeachVideoListData>>(){}.type)

        teachVideoCountInfoAdapter?.addData(mData)

        countInfoView.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.bottom = 30
            }
        })
    }

    override fun release() {
        teachVideoCountInfoAdapter?.cleanData()
        teachVideoCountInfoAdapter = null
    }
}