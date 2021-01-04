package com.luisfelipe.animefinder.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodesBodyResponse(
    val episodes: List<EpisodeResponse>
)