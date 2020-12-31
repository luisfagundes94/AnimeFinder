package com.luisfelipe.animefinder.domain.usecase

import com.luisfelipe.animefinder.domain.repository.AnimeRepository

class GetAnimeDetailsFromApi(private val repository: AnimeRepository) {
    suspend operator fun invoke(id: Int) = repository.getAnimeDetails(id)
}