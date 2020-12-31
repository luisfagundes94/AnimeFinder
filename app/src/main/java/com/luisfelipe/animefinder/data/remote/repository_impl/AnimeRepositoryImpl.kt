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
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Named

class AnimeRepositoryImpl @Inject constructor(
    @Named("service") val jikanService: JikanService
): AnimeRepository {

    private companion object {
        private const val MIN_RESPONSE_CODE = 200
        private const val MAX_RESPONSE_CODE = 299
        private const val REQUEST_TIMEOUT = 5000L
    }

    override suspend fun getPopularAnimes(): RequestStatus<List<Anime>> {
        return withTimeout(REQUEST_TIMEOUT) {
            try {
                val bodyResponse = jikanService.getPopularAnimes()
                if (bodyResponse.code() in MIN_RESPONSE_CODE..MAX_RESPONSE_CODE) {
                    printResponseUrl(bodyResponse)
                    val topAnimeResponseList = bodyResponse.body()?.popularAnimes
                    val topAnimes = topAnimeResponseList?.let { AnimeMapper.mapResponseToDomain(it) }
                    return@withTimeout RequestStatus.Success(topAnimes as List<Anime>)
                } else {
                    printResponseUrl(bodyResponse)
                    return@withTimeout RequestStatus.Error(bodyResponse.message())
                }
            } catch (exception: Exception) {
                throw IllegalArgumentException(exception)
                return@withTimeout RequestStatus.Error(exception.message.toString())
            }
        }
    }

    private fun printResponseUrl(response: Response<BodyResponse>) {
        Log.d("getPopularAnimes", response.raw().request.url.toString())
    }
}