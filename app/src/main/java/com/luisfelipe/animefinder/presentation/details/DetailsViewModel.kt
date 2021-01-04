package com.luisfelipe.animefinder.presentation.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.domain.enums.RequestStatus
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.domain.model.Episode
import com.luisfelipe.animefinder.domain.usecase.GetAnimeDetailsFromApi
import com.luisfelipe.animefinder.domain.usecase.GetAnimeEpisodesFromApi
import kotlinx.coroutines.launch
import javax.inject.Named

class DetailsViewModel @ViewModelInject constructor(
    @Named("getAnimeDetails") private val getAnimeDetailsFromApi: GetAnimeDetailsFromApi,
    @Named("getAnimeEpisodes") private val getAnimeEpisodesFromApi: GetAnimeEpisodesFromApi
) : ViewModel() {

    private val animeDetailsLiveData = MutableLiveData<Anime>()
    val animeDetails: LiveData<Anime> = animeDetailsLiveData

    private val failedToGetAnimeDetailsLiveData = MutableLiveData<Int>()
    val failedToGetAnimeDetails: LiveData<Int> = failedToGetAnimeDetailsLiveData

    private val episodesLiveData = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = episodesLiveData

    private val failedToGetEpisodesLiveData = MutableLiveData<Int>()
    val failedToGetEpisodes: LiveData<Int> = failedToGetEpisodesLiveData

    fun getAnimeDetails(animeId: Int?) = viewModelScope.launch {
        animeId?.let { id ->
            val requestStatus = getAnimeDetailsFromApi(id)
            handleAnimeRequestStatus(requestStatus)
        }
    }

    fun getAnimeEpisodes(animeId: Int?) = viewModelScope.launch {
        animeId?.let { id ->
            val requestStatus = getAnimeEpisodesFromApi(id)
            handleEpisodesRequestStatus(requestStatus)
        }
    }

    private fun handleAnimeRequestStatus(requestStatus: RequestStatus<Anime>) {
        when (requestStatus) {
            is RequestStatus.Success -> animeDetailsLiveData.postValue(requestStatus.data)
            is RequestStatus.Error -> {
                failedToGetAnimeDetailsLiveData.postValue(R.string.warning_failed_to_fetch_anime_details)
            }
            is RequestStatus.InProgress -> {}
        }
    }

    private fun handleEpisodesRequestStatus(requestStatus: RequestStatus<List<Episode>>) {
        when (requestStatus) {
            is RequestStatus.Success -> episodesLiveData.postValue(requestStatus.data)
            is RequestStatus.Error -> {
                failedToGetAnimeDetailsLiveData.postValue(R.string.warning_failed_to_fetch_anime_details)
            }
            is RequestStatus.InProgress -> {}
        }
    }
}