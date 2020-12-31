package com.luisfelipe.animefinder.domain.repository

import com.luisfelipe.animefinder.domain.enums.RequestStatus
import com.luisfelipe.animefinder.domain.model.Anime

interface AnimeRepository {
    suspend fun getPopularAnimes(): RequestStatus<List<Anime>>
    suspend fun getLatestAnimes(): RequestStatus<List<Anime>>
    suspend fun getUpcomingAnimes(): RequestStatus<List<Anime>>
    suspend fun getAnimeDetails(id: Int): RequestStatus<Anime>
}