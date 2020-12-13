package com.antoniokranjcina.imgur.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    @SerializedName("display_name")
    val title: String,
    val followers: Long,
    val description: String?,
    @SerializedName("total_items")
    val totalItems: Long,
    @SerializedName("accent")
    val backgroundColor: String
) : Parcelable
