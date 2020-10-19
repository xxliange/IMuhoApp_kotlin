package com.muhoapp.ui.fragment.homeScrean.mine

import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseFragment
import com.muhoapp.model.domin.home.MineUserCollect
import com.muhoapp.model.domin.home.MineUserHistory
import com.muhoapp.presenter.impl.mine.MinePresenterImpl
import com.muhoapp.ui.adapter.home.MineUserCollectAdapter
import com.muhoapp.ui.adapter.home.MineUserHistoryAdapter
import com.muhoapp.utils.PresenterManager
import com.muhoapp.utils.Utils
import com.muhoapp.view.home.IMineCallback
import com.muhoapp.view.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_mine.*
import okhttp3.internal.Util

class MineFragment : BaseFragment<MinePresenterImpl>(), IMineCallback {
    @BindView(R.id.mine_history_view)
    lateinit var historyView: RecyclerView

    @BindView(R.id.mine_collect_view)
    lateinit var collectView: RecyclerView

    @BindView(R.id.mine_head)
    lateinit var mineHead: LinearLayout

    @BindView(R.id.mine_container)
    lateinit var mineContainer: LinearLayout

    @BindView(R.id.mine_scrollView)
    lateinit var scrollView : NestedScrollView
    private var isShow = false
    private var alpha : Float = 0F
    override fun getSubPresenter(): MinePresenterImpl? {
        return PresenterManager.getMinePresenterImpl()
    }

    override fun getPageLayoutId(): Int {
        return R.layout.fragment_mine
    }

    private var historyAdapter: MineUserHistoryAdapter? = null
    private var collectAdapter: MineUserCollectAdapter? = null
    override fun initView(rootView: View) {
        super.initView(rootView)
        switchUiByState(PageState.SUCCESS)
        val statusBarHeight = Utils.getStatusBarHeight(context)
        mineContainer.setPadding(0, statusBarHeight, 0, 0)
        mineHead.setPadding(Utils.dp2px(context!!,14F).toInt(),statusBarHeight,0,0)
        rootView.findViewById<RelativeLayout>(R.id.mine_history)
            .findViewById<TextView>(R.id.item_mine_list_title).text = "历史记录"
        rootView.findViewById<RelativeLayout>(R.id.mine_history)
            .findViewById<TextView>(R.id.item_mine_list_title)
            .setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(
                    R.drawable.ic_baseline_history_24,
                    null
                ), null, null, null
            )   

        rootView.findViewById<RelativeLayout>(R.id.mine_collect)
            .findViewById<TextView>(R.id.item_mine_list_title).text = "我的收藏"
        rootView.findViewById<RelativeLayout>(R.id.mine_collect)
            .findViewById<TextView>(R.id.item_mine_list_title)
            .setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(
                    R.drawable.ic_baseline_star_border_24,
                    null
                ), null, null, null
            )

        rootView.findViewById<RelativeLayout>(R.id.mine_help)
            .findViewById<TextView>(R.id.item_mine_list_title).text = "帮助中心"
        rootView.findViewById<RelativeLayout>(R.id.mine_help)
            .findViewById<TextView>(R.id.item_mine_list_title)
            .setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(
                    R.drawable.ic_baseline_help_outline_24,
                    null
                ), null, null, null
            )

        rootView.findViewById<RelativeLayout>(R.id.mine_question)
            .findViewById<TextView>(R.id.item_mine_list_title).text = "反馈问题"
        rootView.findViewById<RelativeLayout>(R.id.mine_question)
            .findViewById<TextView>(R.id.item_mine_list_title)
            .setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(
                    R.drawable.ic_baseline_question_answer_24,
                    null
                ), null, null, null
            )

        rootView.findViewById<RelativeLayout>(R.id.mine_setting)
            .findViewById<TextView>(R.id.item_mine_list_title).text = "设置中心"
        rootView.findViewById<RelativeLayout>(R.id.mine_setting)
            .findViewById<TextView>(R.id.item_mine_list_title)
            .setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(
                    R.drawable.ic_baseline_settings_24,
                    null
                ), null, null, null
            )

        rootView.findViewById<RelativeLayout>(R.id.mine_callMe)
            .findViewById<TextView>(R.id.item_mine_list_title).text = "联系我们"
        rootView.findViewById<RelativeLayout>(R.id.mine_callMe)
            .findViewById<TextView>(R.id.item_mine_list_title)
            .setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(
                    R.drawable.ic_baseline_contactless_24,
                    null
                ), null, null, null
            )

        historyAdapter = MineUserHistoryAdapter()
        historyView.adapter = historyAdapter
        historyView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        historyView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = 20
            }
        })
        collectAdapter = MineUserCollectAdapter()
        collectView.adapter = collectAdapter
        collectView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        collectView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = 20
            }
        })


    }

    override fun bindEvent() {
        presenter?.registerViewCallback(this)
        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            val move = scrollY - oldScrollY
            val mo = move/ 100.toFloat()
            if (move > 0){
                if (scrollY > 0){
                    alpha += mo
                    if (alpha > 1){
                        alpha = 1F
                    }
                    mineHead.visibility = View.VISIBLE
                    mineHead.alpha = alpha
                }
            }else{
                if (scrollY < 100){
                    alpha += mo
                    if (alpha < 0.1){
                        alpha = 0F
                        mineHead.visibility = View.GONE
                    }
                    mineHead.alpha = alpha
                }
            }
        })
    }

    override fun loadData() {
        presenter?.getUserCollect()
        presenter?.getUserHistory()
    }

    override fun onLoadUserHistory(data: List<MineUserHistory>) {
        historyAdapter?.addData(data)
    }

    override fun onLoadUserCollect(data: List<MineUserCollect>) {
        collectAdapter?.addData(data)
    }

    override fun release() {
        presenter?.unregisterViewCallback(this)
    }
}