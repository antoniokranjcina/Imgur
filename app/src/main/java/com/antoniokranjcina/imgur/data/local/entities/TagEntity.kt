package com.antoniokranjcina.imgur.data.local.entities

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.antoniokranjcina.imgur.util.Constants.TAGS_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = TAGS_TABLE,
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
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "display_name")
    val title: String,
    val followers: Long,
    val description: String?,
    @ColumnInfo(name = "total_items")
    val totalItems: Long,
    @ColumnInfo(name = "accent")
    val backgroundColor: String
) : Parcelable

