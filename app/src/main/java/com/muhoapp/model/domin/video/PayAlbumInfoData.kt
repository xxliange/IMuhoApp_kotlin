package com.muhoapp.model.domin.video

data class PayAlbumInfoData(
    val SinPrice: Double,
    val albumName: String,
    val coachIntro: String,
    val coachThumb: String,
    val coid: Int,
    val cons: Int,
    val id: Int,
    val info: List<Info>,
    val ispay: Int,
    val name: String,
    val price: Double,
    val priceX: Double,
    val subjectintro: List<Subjectintro>,
    val subscrib: Int,
    val thumb: String,
    val video: String
)

data class Info(
    val content: String,
    val title: String
)

data class Subjectintro(
    val content: String,
    val type: String
)