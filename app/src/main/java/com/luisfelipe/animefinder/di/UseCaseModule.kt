package com.luisfelipe.animefinder.di

import com.luisfelipe.animefinder.domain.repository.AnimeRepository
import com.luisfelipe.animefinder.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Named("getPopularAnimes")
    @Provides
    fun providePopularAnimes(@Named("animeRepository") repository: AnimeRepository) =
        GetPopularAnimesFromApi(repository)

    @Named("getLatestAnimes")
    @Provides
    fun provideLatestAnimes(@Named("animeRepository") repository: AnimeRepository) =
        GetLatestAnimesFromApi(repository)

    @Named("getUpcomingAnimes")
    @Provides
    fun provideUpcomingAnimes(@Named("animeRepository") repository: AnimeRepository) =
        GetUpcomingAnimesFromApi(repository)

    @Named("getAnimeDetails")
    @Provides
    fun provideAnimeDetails(@Named("animeRepository") repository: AnimeRepository) =
        GetAnimeDetailsFromApi(repository)

    @Named("getAnimeEpisodes")
    @Provides
    fun provideAnimeEpisodes(@Named("animeRepository") repository: AnimeRepository) =
        GetAnimeEpisodesFromApi(repository)

}