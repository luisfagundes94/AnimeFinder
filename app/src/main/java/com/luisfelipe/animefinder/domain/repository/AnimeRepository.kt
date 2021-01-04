package com.luisfelipe.animefinder.domain.repository

import com.luisfelipe.animefinder.domain.enums.RequestStatus
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.domain.model.Episode

interface AnimeRepository {
    suspend fun getPopularAnimes(): RequestStatus<List<Anime>>
    suspend fun getLatestAnimes(): RequestStatus<List<Anime>>
    suspend fun getUpcomingAnimes(): RequestStatus<List<Anime>>
    suspend fun getAnimeDetails(id: Int): RequestStatus<Anime>
    suspend fun getAnimeEpisodes(animeId: Int): RequestStatus<List<Episode>>
}