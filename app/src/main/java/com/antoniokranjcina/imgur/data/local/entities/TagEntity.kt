package com.antoniokranjcina.imgur.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniokranjcina.imgur.util.Constants.TAGS_TABLE

@Entity(tableName = TAGS_TABLE)
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
)
