package com.antoniokranjcina.imgur.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImgurResponse(
    val data: Data,
    val success: Boolean,
    val stats: Int
) : Parcelable