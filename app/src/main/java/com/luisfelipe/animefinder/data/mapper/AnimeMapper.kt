package com.luisfelipe.animefinder.data.mapper

import com.luisfelipe.animefinder.data.remote.model.AnimeResponse
import com.luisfelipe.animefinder.domain.model.Anime

class AnimeMapper {
    companion object {
        fun mapResponseToDomain(animeResponseList: List<AnimeResponse>): List<Anime> {
            val animeList = mutableListOf<Anime>()
            for (animeResponse in animeResponseList) {
                val anime = Anime(
                    id = animeResponse.id,
                    title = animeResponse.title,
                    imageUrl = animeResponse.image_url,
                    episodes = animeResponse.episodes ?: 0,
                    score = animeResponse.score
                )
                animeList.add(anime)
            }
            return animeList
        }
    }
}