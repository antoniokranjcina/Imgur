package com.antoniokranjcina.imgur.data.network.model

import com.google.gson.annotations.SerializedName

data class Data(
    val name: String,
    val followers: Long,
    @SerializedName("total_items")
    val totalItem: Long,
    val description: String,
    @SerializedName("items")
    val posts: List<Post>
)