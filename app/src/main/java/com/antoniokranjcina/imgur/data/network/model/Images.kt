package com.antoniokranjcina.imgur.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val id: String,
    val description: String?,
    val type: String,
    val link: String,
    @SerializedName("gifv")
    val gif: String?,
    val views: String,
    val width: Long,
    val height: Long
) : Parcelable