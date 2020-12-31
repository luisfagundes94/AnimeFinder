package com.luisfelipe.animefinder.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeResponse(
    @Json(name = "mal_id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "image_url") val image_url: String,
    val episodes: Int?,
    val score: Float
)
