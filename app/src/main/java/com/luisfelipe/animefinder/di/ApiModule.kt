package com.luisfelipe.animefinder.di

import com.luisfelipe.animefinder.BuildConfig
import com.luisfelipe.animefinder.data.remote.repository_impl.AnimeRepositoryImpl
import com.luisfelipe.animefinder.data.service.JikanService
import com.luisfelipe.animefinder.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {

    private const val JIKAN_BASE_URL = "https://api.jikan.moe/v3/"

    @Named("animeRepository")
    @Provides
    fun provideAnimeRepositoryImpl(@Named("service") jikanService: JikanService): AnimeRepository =
        AnimeRepositoryImpl(jikanService)

    @Named("okHttpClient")
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Named("retrofit")
    @Singleton
    @Provides
    fun provideJikanRetrofit(@Named("okHttpClient") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(JIKAN_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Named("service")
    @Singleton
    @Provides
    fun provideJikanService(@Named("retrofit") retrofit: Retrofit): JikanService =
        retrofit.create(JikanService::class.java)
}