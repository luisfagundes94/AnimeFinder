package com.luisfelipe.animefinder.data.service

import com.luisfelipe.animefinder.data.remote.model.AnimeResponse
import com.luisfelipe.animefinder.data.remote.model.BodyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanService {

    @GET("top/anime/1/bypopularity")
    suspend fun getPopularAnimes(): Response<BodyResponse>

    @GET("top/anime/1/airing")
    suspend fun getLatestAnimes(): Response<BodyResponse>

    @GET("top/anime/1/upcoming")
    suspend fun getUpcomingAnimes(): Response<BodyResponse>

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): Response<AnimeResponse>

}