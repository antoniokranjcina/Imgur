package com.antoniokranjcina.imgur.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val name: String,
    val followers: Long,
    @SerializedName("total_items")
    val totalItem: Long,
    val description: String,
    @SerializedName("items")
    val posts: List<Post>
) : Parcelable