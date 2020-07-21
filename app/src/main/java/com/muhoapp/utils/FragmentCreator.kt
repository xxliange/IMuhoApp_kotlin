package com.muhoapp.utils

import androidx.fragment.app.Fragment
import com.muhoapp.ui.fragment.homeScrean.home.HomeFragment
import com.muhoapp.ui.fragment.homeScrean.hot.HotFragment
import com.muhoapp.ui.fragment.homeScrean.mine.MineFragment
import com.muhoapp.ui.fragment.homeScrean.teach.TeachFragment
import com.muhoapp.ui.fragment.homeScrean.vip.VipFragment

class FragmentCreator {
    companion object {
        const val INDEX_HOME = 1
        const val INDEX_TEACH = 2
        const val INDEX_VIP = 3
        const val INDEX_HOT = 4
        const val INDEX_MINE = 5

        private val homeFragment: HomeFragment by lazy {
            HomeFragment()
        }

        private val teachFragment: TeachFragment by lazy {
            TeachFragment()
        }

        private val vipFragment: VipFragment by lazy {
            VipFragment()
        }

        private val hotFragment: HotFragment by lazy {
            HotFragment()
        }

        private val mineFragment: MineFragment by lazy {
            MineFragment()
        }

        fun getFragment(index: Int): Fragment? {
            when (index) {
                INDEX_HOME -> {
                    return homeFragment
                }
                INDEX_TEACH -> {
                    return teachFragment
                }
                INDEX_VIP -> {
                    return vipFragment
                }
                INDEX_HOT -> {
                    return hotFragment
                }
                INDEX_MINE -> {
                    return mineFragment
                }
            }
            return null
        }
    }
}