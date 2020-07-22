package com.muhoapp.model.domin.home

data class UpdateData(
    val c_type: Int,
    val cid: Int,
    val collection: Int,
    val comment: Int,
    val iscollect: Boolean,
    val photo: List<String>,
    val tags: List<String>,
    val thumb: String,
    val thumb1: String,
    val thumb2: String,
    val title: String,
    val update_time: String,
    val video: String,
    val view: Int
)