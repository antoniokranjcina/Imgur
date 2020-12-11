package com.antoniokranjcina.imgur.data.network.model

data class ImgurResponse(
    val data: Data,
    val success: Boolean,
    val stats: Int
)