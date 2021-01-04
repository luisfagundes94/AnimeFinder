package com.luisfelipe.animefinder.data.mapper

import com.luisfelipe.animefinder.data.remote.model.AnimeResponse
import com.luisfelipe.animefinder.data.remote.model.EpisodeResponse
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.domain.model.Episode

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
                    score = animeResponse.score ?: 0.0f,
                )
                animeList.add(anime)
            }
            return animeList
        }

        fun mapResponseToDomain(animeDetailsResponse: AnimeResponse): Anime {
            return Anime(
                id = animeDetailsResponse.id,
                title = animeDetailsResponse.title,
                summary = animeDetailsResponse.synopsis ?: "",
                imageUrl = animeDetailsResponse.image_url,
                episodes = animeDetailsResponse.episodes ?: 0,
                score = animeDetailsResponse.score ?: 0.0f,
                duration = animeDetailsResponse.duration ?: "",
                status = animeDetailsResponse.status ?: "",
                source = animeDetailsResponse.source ?: "",
                premiered = animeDetailsResponse.premiered ?: "",
                rating = animeDetailsResponse.rating ?: ""
            )
        }

        fun mapEpisodeResponseListToDomain(episodeResponseList: List<EpisodeResponse>): List<Episode> {
            val episodes = mutableListOf<Episode>()
            for (episodeResponse in episodeResponseList) {
                val episode = Episode(
                    episode_id = episodeResponse.episode_id,
                    title = episodeResponse.title,
                    releaseDate = episodeResponse.aired,
                    isFiller = episodeResponse.filler
                )
                episodes.add(episode)
            }
            return episodes
        }
    }
}