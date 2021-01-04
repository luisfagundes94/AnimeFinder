package com.luisfelipe.animefinder.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeResponse(
    val episode_id: Int,
    val title: String,
    val aired: String,
    val filler: Boolean
)
