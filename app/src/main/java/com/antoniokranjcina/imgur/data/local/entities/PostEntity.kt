package com.antoniokranjcina.imgur.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniokranjcina.imgur.data.network.model.Image
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.data.network.model.Tag
import com.antoniokranjcina.imgur.util.Constants.POSTS_TABLE

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
    val images: List<Image>
)

fun List<PostEntity>.entitiesToPosts(): List<Post> {
    val list = mutableListOf<Post>()
    this.forEach {
        list += Post(
            id = it.id,
            title = it.title,
            accountUrl = it.accountUrl,
            description = it.description.toString(),
            datetime = it.datetime,
            views = it.views,
            ups = it.ups,
            downs = it.downs,
            commentCount = it.commentCount,
            imagesCount = it.imagesCount,
            tags = it.tags,
            images = it.images
        )
    }
    return list
}