package com.luisfelipe.animefinder.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val duration: String? = "",
    val rating: String? = "",
    val premiered: String? = "",
    val source: String? = "",
    val status: String? = "",
    val imageUrl: String,
    val episodes: Int? = 0,
    val score: Float,
    val summary: String? = "",
)