package com.antoniokranjcina.imgur.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.antoniokranjcina.imgur.data.local.daos.ImagesDao
import com.antoniokranjcina.imgur.data.local.daos.PostsDao
import com.antoniokranjcina.imgur.data.local.daos.TagsDao
import com.antoniokranjcina.imgur.data.local.entities.ImageEntity
import com.antoniokranjcina.imgur.data.local.entities.PostEntity
import com.antoniokranjcina.imgur.data.local.entities.TagEntity
import com.antoniokranjcina.imgur.util.Constants.DATABASE_NAME

@Database(
    entities = [ImageEntity::class, PostEntity::class, TagEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class PostsDatabase : RoomDatabase() {

    abstract val imagesDao: ImagesDao
    abstract val postsDao: PostsDao
    abstract val tagsDao: TagsDao

    companion object {
        private var INSTANCE: PostsDatabase? = null

        fun getDatabase(context: Context): PostsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}