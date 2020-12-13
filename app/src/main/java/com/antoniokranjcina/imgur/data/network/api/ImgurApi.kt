package com.antoniokranjcina.imgur.data.network.api

import com.antoniokranjcina.imgur.data.network.model.ImgurResponse
import com.antoniokranjcina.imgur.util.Constants.BASE_URL
import com.antoniokranjcina.imgur.util.Constants.CLIENT_ID
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImgurApi {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ImgurApi = retrofit.create(ImgurApi::class.java)
    }

    @Headers("Authorization: Client-ID $CLIENT_ID")
    @GET("gallery/t/dogs?page=0&perPage=10")
    suspend fun getImgurResponse(): Response<ImgurResponse>
}