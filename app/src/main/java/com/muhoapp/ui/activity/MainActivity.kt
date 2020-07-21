package com.muhoapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import butterknife.BindView
import com.muhoapp.R
import com.muhoapp.base.BaseActivity
import com.muhoapp.utils.ContextExtensions
import com.muhoapp.utils.FragmentCreator

class MainActivity : BaseActivity<Any>(), View.OnClickListener, IActivityForFragment {
    @BindView(R.id.main_bottom_bar)
    lateinit var bottomBar: LinearLayout

    private var lastFragmentIndex = -1

    override fun initView() {
        setTransparentStatusBar(true)
        val supportFragmentManager = supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()

        val homeFragment = FragmentCreator.getFragment(FragmentCreator.INDEX_HOME)
        beginTransaction.add(
            R.id.page_container,
            homeFragment!!,
            FragmentCreator.INDEX_HOME.toString()
        )
        lastFragmentIndex = FragmentCreator.INDEX_HOME

        val teachFragment = FragmentCreator.getFragment(FragmentCreator.INDEX_TEACH)
        beginTransaction.add(
            R.id.page_container,
            teachFragment!!,
            FragmentCreator.INDEX_TEACH.toString()
        )
        beginTransaction.hide(teachFragment)

        val vipFragment = FragmentCreator.getFragment(FragmentCreator.INDEX_VIP)
        beginTransaction.add(
            R.id.page_container,
            vipFragment!!,
            FragmentCreator.INDEX_VIP.toString()
        )
        beginTransaction.hide(vipFragment)

        val hotFragment = FragmentCreator.getFragment(FragmentCreator.INDEX_HOT)
        beginTransaction.add(
            R.id.page_container,
            hotFragment!!,
            FragmentCreator.INDEX_HOT.toString()
        )
        beginTransaction.hide(hotFragment)

        val mineFragment = FragmentCreator.getFragment(FragmentCreator.INDEX_MINE)
        beginTransaction.add(
            R.id.page_container,
            mineFragment!!,
            FragmentCreator.INDEX_MINE.toString()
        )
        beginTransaction.hide(mineFragment)
        beginTransaction.commit()

    }

    override fun getSubPresenter(): Any? {
        return null
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initListener() {
        for (i in 0 until bottomBar.childCount) {
            bottomBar.getChildAt(i).setOnClickListener(this);
        }
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.navigation_home -> {
                setUpFragment(FragmentCreator.INDEX_HOME, null)
            }
            R.id.navigation_teach -> {
                setUpFragment(FragmentCreator.INDEX_TEACH, null)
            }
            R.id.navigation_vip -> {
                setUpFragment(FragmentCreator.INDEX_VIP, null)
            }
//            R.id.navigation_hot -> {
//                setUpFragment(FragmentCreator.INDEX_HOT, null)
//            }
            R.id.navigation_mine -> {
                setUpFragment(FragmentCreator.INDEX_MINE, null)
            }
        }
    }

    override fun setUpFragment(index: Int, bundle: Bundle?) {
        if (index == lastFragmentIndex) {
            return
        }
        val fragment = FragmentCreator.getFragment(index)
        fragment?.arguments = bundle
        if (fragment != null) {
            val supportFragmentManager = supportFragmentManager
            val transaction = supportFragmentManager.beginTransaction()
            if (lastFragmentIndex != -1) {
                val lastFragment = FragmentCreator.getFragment(lastFragmentIndex)
                transaction.hide(lastFragment!!)
            }
            if (!fragment.isAdded) {
                transaction.add(R.id.page_container,fragment,index.toString())
            }else{
                transaction.show(fragment)
            }
            lastFragmentIndex = index
            transaction.commit()
        }
        upDateNavigationCheckedByPosition(index)
    }

    private fun upDateNavigationCheckedByPosition(index: Int) {
        for(i in 0 until bottomBar.childCount){
            val child = bottomBar.getChildAt(i)
            if (i == index-1) {
                Log.d("minactiv" , "index --> $i")
                setEnable(child, false)
            }else {
                Log.d("minactiv" , "noindex --> $i")
                setEnable(child, true)
            }
        }
        when (index) {
            FragmentCreator.INDEX_HOME -> {

            }
            FragmentCreator.INDEX_TEACH ->{

            }
            FragmentCreator.INDEX_VIP ->{

            }
            FragmentCreator.INDEX_HOT ->{

            }
            FragmentCreator.INDEX_MINE ->{

            }
        }
    }

    private fun setEnable(child: View, isEnable: Boolean) {
        child.isEnabled = isEnable
        if (child is ViewGroup) {
            for (i in 0 until child.childCount){
                child.getChildAt(i).isEnabled = isEnable
            }
        }
    }
}