package com.antoniokranjcina.imgur.data.network.model

data class Post(
    val id: String,
    val title: String,
    val descriptor: String,
    val datetime: Long,
    val ups: Long,
    val downs: Long,
    val images: List<Images>
)