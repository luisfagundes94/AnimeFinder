package com.luisfelipe.animefinder.di

import com.luisfelipe.animefinder.presentation.home.AnimeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {

    @ActivityScoped
    @Named("popularAnimesAdapter")
    @Provides
    fun providePopularAnimesAdapter() = AnimeAdapter()

    @ActivityScoped
    @Named("latestAnimesAdapter")
    @Provides
    fun provideLatestAnimesAdapter() = AnimeAdapter()

    @ActivityScoped
    @Named("upcomingAnimesAdapter")
    @Provides
    fun provideUpcomingAnimesAdapter() = AnimeAdapter()

}