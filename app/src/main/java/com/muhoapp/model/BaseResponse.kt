package com.muhoapp.model

data class BaseResponse<T>(
    val `data`: T,
    val message: String,
    val stateCode: Int
)