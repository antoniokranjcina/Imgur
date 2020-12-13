package com.antoniokranjcina.imgur.data.network.model

import com.google.gson.annotations.SerializedName

data class Post(
    val id: String,
    val title: String,
    @SerializedName("account_url")
    val accountUrl: String,
    val description: String,
    val datetime: Long,
    val ups: Long,
    val downs: Long,
    @SerializedName("comment_count")
    val commentCount: String,
    @SerializedName("images_count")
    val imagesCount: Long,
    val tags: List<Tags>,
    val images: List<Images>
)