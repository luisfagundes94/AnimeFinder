package com.luisfelipe.animefinder.presentation.details.episodes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.domain.enums.RequestStatus
import com.luisfelipe.animefinder.domain.model.Episode
import com.luisfelipe.animefinder.domain.usecase.GetAnimeEpisodesFromApi
import kotlinx.coroutines.launch
import javax.inject.Named

class EpisodesViewModel @ViewModelInject constructor(
    @Named("getAnimeEpisodes") private val getAnimeEpisodesFromApi: GetAnimeEpisodesFromApi
) : ViewModel() {

    private val episodesLiveData = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = episodesLiveData

    private val failedToGetEpisodesLiveData = MutableLiveData<Int>()
    val failedToGetEpisodes: LiveData<Int> = failedToGetEpisodesLiveData

    fun getAnimeEpisodes(animeId: Int) = viewModelScope.launch {
        val requestStatus = getAnimeEpisodesFromApi(animeId)
        handleRequestStatus(requestStatus)
    }

    private fun handleRequestStatus(requestStatus: RequestStatus<List<Episode>>) {
        when (requestStatus) {
            is RequestStatus.Success -> episodesLiveData.postValue(requestStatus.data)
            is RequestStatus.Error -> failedToGetEpisodesLiveData.postValue(R.string.warning_failed_to_retrieve_episodes)
            is RequestStatus.InProgress -> {}
        }
    }
}