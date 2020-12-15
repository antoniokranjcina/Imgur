package com.antoniokranjcina.imgur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniokranjcina.imgur.util.Constants.IMAGES_TABLE

@Entity(tableName = IMAGES_TABLE)
data class ImageEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: String?,
    val type: String,
    val link: String,
    val gifv: String?,
    val views: String,
    val width: Long,
    val height: Long
)
