package com.antoniokranjcina.imgur.data.network.model

data class Images(
    val id: String,
    val type: String,
    val link: String,
    val views: String,
    val width: Long,
    val height: Long
)