package com.muhoapp.model.domin.home

data class BannerData(
    val `data`: Data,
    val id: Int,
    val pagetype: Int,
    val pic: String,
    val title: String
) {
    override fun toString(): String {
        return "BannerData(`data`=$`data`, id=$id, pagetype=$pagetype, pic='$pic', title='$title')"
    }
}

data class Data(
    val cid: Int,
    val playerid: Int
)