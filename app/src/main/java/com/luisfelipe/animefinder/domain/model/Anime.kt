package com.luisfelipe.animefinder.domain.model

data class Anime(
    val title: String,
    val imageUrl: String,
    val episodes: Int,
    val score: Float,
)