package com.luisfelipe.animefinder.presentation.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.domain.enums.RequestStatus
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.domain.usecase.GetAnimeDetailsFromApi
import kotlinx.coroutines.launch
import javax.inject.Named

class DetailsViewModel @ViewModelInject constructor(
    @Named("getAnimeDetails") private val getAnimeDetailsFromApi: GetAnimeDetailsFromApi
) : ViewModel() {

    private val animeDetailsLiveData = MutableLiveData<Anime>()
    val animeDetails: LiveData<Anime> = animeDetailsLiveData

    private val failedToGetAnimeDetailsLiveData = MutableLiveData<Int>()
    val failedToGetAnimeDetails: LiveData<Int> = failedToGetAnimeDetailsLiveData

    fun getAnimeDetails(id: Int?) = viewModelScope.launch {
        if (id != null) {
            val requestStatus = getAnimeDetailsFromApi(id)
            handleRequestStatus(requestStatus)
        }
    }

    private fun handleRequestStatus(requestStatus: RequestStatus<Anime>) {
        when (requestStatus) {
            is RequestStatus.Success -> animeDetailsLiveData.postValue(requestStatus.data)
            is RequestStatus.Error -> {
                failedToGetAnimeDetailsLiveData.postValue(R.string.warning_failed_to_fetch_anime_details)
            }
            is RequestStatus.InProgress -> {}
        }
    }
}