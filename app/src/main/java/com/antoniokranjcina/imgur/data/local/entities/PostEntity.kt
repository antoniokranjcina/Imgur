package com.antoniokranjcina.imgur.data.local.entities

import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniokranjcina.imgur.data.network.model.Image
import com.antoniokranjcina.imgur.data.network.model.Tag
import com.antoniokranjcina.imgur.util.Constants.POSTS_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = POSTS_TABLE)
data class PostEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    @ColumnInfo(name = "account_url")
    val accountUrl: String,
    val description: String?,
    val datetime: Long,
    val views: Long,
    val ups: Long,
    val downs: Long,
    @ColumnInfo(name = "comment_count")
    val commentCount: Long,
    @ColumnInfo(name = "images_count")
    val imagesCount: Long,
    val tags: List<Tag>,
    val images: List<Image>?
) : Parcelable

fun List<PostEntity>.checkIfAllPostsHaveImages(): List<PostEntity> {
    val list = mutableListOf<PostEntity>()

    this.forEach { post ->
        try {
            if (!post.images!!.isNullOrEmpty()) {
                list += post
            }
        } catch (e: Exception) {
            Log.d("ExtensionFunction", "checkIfAllPostsHaveImages: ${e.localizedMessage}")
        }
    }

    return list
}