package com.antoniokranjcina.imgur.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val id: String,
    val description: String?,
    val type: String,
    val link: String,
    val gifv: String?,
    val views: String,
    val width: Long,
    val height: Long
) : Parcelable