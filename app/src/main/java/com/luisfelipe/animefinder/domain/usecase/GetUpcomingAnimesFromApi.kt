package com.luisfelipe.animefinder.domain.usecase

import com.luisfelipe.animefinder.domain.repository.AnimeRepository

class GetUpcomingAnimesFromApi(private val repository: AnimeRepository) {
    suspend operator fun invoke() = repository.getUpcomingAnimes()
}