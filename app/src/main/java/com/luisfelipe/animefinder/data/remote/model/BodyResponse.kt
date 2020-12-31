package com.luisfelipe.animefinder.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BodyResponse(
    @Json(name = "top")
    val animes: List<AnimeResponse>
)
