package com.luisfelipe.animefinder.domain.model

data class Episode(
    val episode_id: Int,
    val title: String,
    val releaseDate: String,
    val isFiller: Boolean
) {
    fun getFormattedReleaseDate(): String {
        val actualDate = this.releaseDate.substring(0, 10)
        val splittedDate = actualDate.split("-")
        return splittedDate.reversed().joinToString("/")
    }
}