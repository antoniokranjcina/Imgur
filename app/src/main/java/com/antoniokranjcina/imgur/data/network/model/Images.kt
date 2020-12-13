package com.antoniokranjcina.imgur.data.network.model

import com.google.gson.annotations.SerializedName

data class Images(
    val id: String,
    val description: String,
    val type: String,
    val link: String,
    @SerializedName("gifv")
    val gif: String?,
    val views: String,
    val width: Long,
    val height: Long
)