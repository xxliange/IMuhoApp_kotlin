package com.muhoapp.view.home

import com.muhoapp.model.domin.home.BannerData

interface IHomeCallback {
    fun onLoadBannerData(data : BannerData?)
}