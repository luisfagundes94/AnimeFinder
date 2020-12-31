package com.luisfelipe.animefinder.data.remote.repository_impl

import android.util.Log
import com.luisfelipe.animefinder.data.mapper.AnimeMapper
import com.luisfelipe.animefinder.data.remote.model.BodyResponse
import com.luisfelipe.animefinder.data.service.JikanService
import com.luisfelipe.animefinder.domain.enums.RequestStatus
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.domain.repository.AnimeRepository
import kotlinx.coroutines.withTimeout
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class AnimeRepositoryImpl @Inject constructor(
    @Named("service") val jikanService: JikanService
) : AnimeRepository {

    private companion object {
        private const val MIN_RESPONSE_CODE = 200
        private const val MAX_RESPONSE_CODE = 299
        private const val REQUEST_TIMEOUT = 5000L
    }

    override suspend fun getPopularAnimes(): RequestStatus<List<Anime>> {
        return getAnimes(jikanService.getPopularAnimes())
    }

    override suspend fun getLatestAnimes(): RequestStatus<List<Anime>> {
        return getAnimes(jikanService.getLatestAnimes())
    }

    override suspend fun getUpcomingAnimes(): RequestStatus<List<Anime>> {
        return getAnimes(jikanService.getUpcomingAnimes())
    }

    private suspend fun getAnimes(bodyResponse: Response<BodyResponse>): RequestStatus<List<Anime>> {
        return withTimeout(REQUEST_TIMEOUT) {
            try {
                if (bodyResponse.code() in MIN_RESPONSE_CODE..MAX_RESPONSE_CODE) {
                    val animeResponseList = bodyResponse.body()?.animes
                    val animes =
                        animeResponseList?.let { AnimeMapper.mapResponseToDomain(it) }
                    return@withTimeout RequestStatus.Success(animes as List<Anime>)
                } else {
                    return@withTimeout RequestStatus.Error(bodyResponse.message())
                }
            } catch (exception: Exception) {
                Log.d("getAnimes", exception.message.toString())
                return@withTimeout RequestStatus.Error(exception.message.toString())
            }
        }
    }
}