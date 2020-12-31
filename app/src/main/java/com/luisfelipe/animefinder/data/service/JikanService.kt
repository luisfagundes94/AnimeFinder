package com.luisfelipe.animefinder.data.service

import com.luisfelipe.animefinder.data.remote.model.BodyResponse
import retrofit2.Response
import retrofit2.http.GET

interface JikanService {

    @GET("top/anime/1/bypopularity")
    suspend fun getPopularAnimes(): Response<BodyResponse>

    @GET("top/anime/1/airing")
    suspend fun getLatestAnimes(): Response<BodyResponse>

    @GET("top/anime/1/upcoming")
    suspend fun getUpcomingAnimes(): Response<BodyResponse>

}