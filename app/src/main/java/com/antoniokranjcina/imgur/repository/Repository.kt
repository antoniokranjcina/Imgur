package com.antoniokranjcina.imgur.repository

import com.antoniokranjcina.imgur.data.network.api.ImgurApi
import com.antoniokranjcina.imgur.data.network.model.Post

class Repository {
    suspend fun getPosts(): List<Post>? = ImgurApi.service.getImgurResponse().body()?.data?.posts
}