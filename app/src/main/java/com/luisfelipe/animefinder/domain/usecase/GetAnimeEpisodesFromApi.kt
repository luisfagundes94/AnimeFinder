package com.luisfelipe.animefinder.domain.usecase

import com.luisfelipe.animefinder.domain.repository.AnimeRepository

class GetAnimeEpisodesFromApi(private val repository: AnimeRepository) {
    suspend operator fun invoke(animeId: Int) = repository.getAnimeEpisodes(animeId)
}