package com.antoniokranjcina.imgur.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antoniokranjcina.imgur.data.local.entities.TagEntity

@Dao
interface TagsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTags(tagsEntity: TagEntity)

    @Query("SELECT * FROM imgur_tags_table")
    suspend fun readAllTagsFromDatabase(): List<TagEntity>

}