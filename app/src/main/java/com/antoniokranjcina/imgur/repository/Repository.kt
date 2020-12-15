package com.antoniokranjcina.imgur.repository

import androidx.lifecycle.LiveData
import com.antoniokranjcina.imgur.data.local.PostsDatabase
import com.antoniokranjcina.imgur.data.local.entities.PostEntity
import com.antoniokranjcina.imgur.data.network.api.ImgurApi
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.data.network.model.postsToEntities

class Repository(private val postsDatabase: PostsDatabase) {

    val posts: LiveData<List<PostEntity>> = postsDatabase.postsDao.readAllPostsFromDatabase()

    private suspend fun getPostsFromApi(): List<Post>? = ImgurApi.service.getImgurResponse().body()?.data?.posts

    private suspend fun insertPostsInDatabase(postEntities: List<PostEntity>) {
        postsDatabase.postsDao.insertAllPosts(postEntities)
    }

    suspend fun getPosts() {
        val response = getPostsFromApi()

        if (response != null) {
            insertPostsInDatabase(response.postsToEntities())
        }
    }
}