package com.muhoapp.model.domin.video

data class PayAlbumVideoData(
    val cid: Int,
    val collection: Int,
    val isfree: Int,
    val name: String,
    val share_url: String,
    val status: Int,
    val tags: String,
    val thumb: String,
    val title: String,
    val video: String,
    val view: Int
)