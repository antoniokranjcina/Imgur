package com.antoniokranjcina.imgur.data.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.antoniokranjcina.imgur.util.Constants.IMAGES_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = IMAGES_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = PostEntity::class,
            parentColumns = ["id"],
            childColumns = ["postId"],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index(
            value = ["postId"]
        )
    ]
)
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
) : Parcelable
