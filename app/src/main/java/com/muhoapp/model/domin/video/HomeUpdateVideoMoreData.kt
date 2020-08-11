package com.muhoapp.model.domin.video

data class HomeUpdateVideoMoreData(
    val c_type: Int,
    val cid: Int,
    val photo: List<String>,
    val tags: String,
    val thumb: List<String>,
    val thumb1: String,
    val title: String,
    val update_time: String,
    val video: String
)