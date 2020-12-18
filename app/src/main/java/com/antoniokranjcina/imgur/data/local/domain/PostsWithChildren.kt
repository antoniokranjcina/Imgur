package com.antoniokranjcina.imgur.data.local.domain

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.antoniokranjcina.imgur.data.local.entities.ImageEntity
import com.antoniokranjcina.imgur.data.local.entities.PostEntity
import com.antoniokranjcina.imgur.data.local.entities.TagEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostsWithChildren(
    @Embedded var post: PostEntity,
    @Relation(entity = ImageEntity::class, parentColumn = "id", entityColumn = "postId")
    var images: List<ImageEntity>,
    @Relation(entity = TagEntity::class, parentColumn = "id", entityColumn = "postId")
    var tagEntity: List<TagEntity>
) : Parcelable
