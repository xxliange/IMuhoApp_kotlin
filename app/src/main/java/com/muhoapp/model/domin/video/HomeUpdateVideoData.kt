package com.muhoapp.model.domin.video

data class HomeUpdateVideoData(
    val c_type: Int,
    val cid: Int,
    val collection: Int,
    val comment: Int,
    val `data`: Data,
    val iscollect: Boolean,
    val pagetext: String,
    val pagetype: Int,
    val photo: List<String>,
    val share_url: String,
    val tags: String,
    val thumb: List<String>,
    val thumb1: String,
    val title: String,
    val update_time: String,
    val video: String,
    val view: Int
)

data class Data(
    val cid: Int,
    val playerid: Int
)