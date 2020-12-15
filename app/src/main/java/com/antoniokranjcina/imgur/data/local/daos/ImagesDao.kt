package com.antoniokranjcina.imgur.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antoniokranjcina.imgur.data.local.entities.ImageEntity

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImages(imagesEntity: List<ImageEntity>)

    @Query("SELECT * FROM imgur_images_table")
    fun readAllImagesFromDatabase(): List<ImageEntity>
}