package com.muhoapp.view.home

import com.muhoapp.model.domin.home.MineUserCollect
import com.muhoapp.model.domin.home.MineUserHistory

interface IMineCallback {
    fun onLoadUserHistory(data: List<MineUserHistory>)
    fun onLoadUserCollect(data: List<MineUserCollect>)
}