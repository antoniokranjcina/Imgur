package com.antoniokranjcina.imgur.data.local

import androidx.room.TypeConverter
import com.antoniokranjcina.imgur.data.network.model.Image
import com.antoniokranjcina.imgur.data.network.model.Tag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    /**
     * Tags
     */
    @TypeConverter
    fun tagsToString(tags: List<Tag>): String = Gson().toJson(tags)

    @TypeConverter
    fun stringToTags(data: String): List<Tag> {
        val listType = object : TypeToken<List<Tag>>() {}.type
        return Gson().fromJson(data, listType)
    }


    /**
     * Images
     */
    @TypeConverter
    fun imagesToString(images: List<Image>): String = Gson().toJson(images)

    @TypeConverter
    fun stringToPhoto(data: String): List<Image> {
        val listType = object : TypeToken<List<Image>>() {}.type
        return Gson().fromJson(data, listType)
    }

}