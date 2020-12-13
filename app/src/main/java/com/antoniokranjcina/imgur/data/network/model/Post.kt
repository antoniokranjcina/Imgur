package com.antoniokranjcina.imgur.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: String,
    val title: String,
    @SerializedName("account_url")
    val accountUrl: String,
    val description: String,
    val datetime: Long,
    val views: Long,
    val ups: Long,
    val downs: Long,
    @SerializedName("comment_count")
    val commentCount: String,
    @SerializedName("images_count")
    val imagesCount: Long,
    val tags: List<Tag>,
    val images: List<Images>
) : Parcelable