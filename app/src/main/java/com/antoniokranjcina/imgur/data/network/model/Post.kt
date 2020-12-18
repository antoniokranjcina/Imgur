package com.antoniokranjcina.imgur.data.network.model

import android.os.Parcelable
import com.antoniokranjcina.imgur.data.local.entities.PostEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: String,
    val title: String,
    @SerializedName("account_url")
    val accountUrl: String,
    val description: String,
    val datetime: Long,
    val views: Long,
    val ups: Long,
    val downs: Long,
    @SerializedName("comment_count")
    val commentCount: Long,
    @SerializedName("images_count")
    val imagesCount: Long,
    val tags: List<Tag>,
    val images: List<Image>
) : Parcelable

fun List<Post>.postsToEntities(): List<PostEntity> {
    val list = mutableListOf<PostEntity>()
    this.forEach {
        list += PostEntity(
            id = it.id,
            title = it.title,
            accountUrl = it.accountUrl,
            description = it.description,
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

fun Post.asEntityModel(): PostEntity {
    return PostEntity(
        id = this.id,
        title = this.title,
        accountUrl = this.accountUrl,
        description = this.description,
        datetime = this.datetime,
        views = this.views,
        ups = this.ups,
        downs = this.downs,
        commentCount = this.commentCount,
        imagesCount = this.imagesCount,
        tags = this.tags,
        images = this.images
    )
}