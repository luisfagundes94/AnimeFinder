package com.luisfelipe.animefinder.domain.usecase

import com.luisfelipe.animefinder.domain.repository.AnimeRepository

class GetLatestAnimesFromApi(private val repository: AnimeRepository) {
    suspend operator fun invoke() = repository.getLatestAnimes()
}