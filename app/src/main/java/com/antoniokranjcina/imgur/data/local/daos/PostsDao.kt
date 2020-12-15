package com.antoniokranjcina.imgur.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antoniokranjcina.imgur.data.local.entities.PostEntity

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(postEntity: List<PostEntity>)

    @Query("SELECT * FROM imgur_posts_table ORDER BY id DESC")
    fun readAllPostsFromDatabase(): LiveData<List<PostEntity>>

}