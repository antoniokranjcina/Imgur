package com.antoniokranjcina.imgur.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antoniokranjcina.imgur.data.local.PostsDatabase
import com.antoniokranjcina.imgur.data.local.entities.PostEntity
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.data.network.model.postsToEntities

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(postEntity: PostEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(postsEntities: List<PostEntity>)

    @Query("SELECT * FROM imgur_posts_table")
    fun readAllPostsFromDatabase(): LiveData<List<PostEntity>>

    fun insertPostsWithChildren(posts: List<Post>, database: PostsDatabase) {
        insertAllPosts(posts.postsToEntities())
        posts.forEach { post ->
            post.images.forEach { image ->
                val databaseModel = ""
                val postId = ""
                val databaseSize = ""
            }
        }
    }
}