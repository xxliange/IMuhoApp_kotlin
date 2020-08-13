package com.muhoapp.model.domin.home

data class MineUserCollect(
    val c_type: Int,
    val cid: Int,
    val media: List<String>,
    val name: String,
    val sid: Int,
    val thumb: String,
    val title: String,
    val type: Int,
    val video: String
)