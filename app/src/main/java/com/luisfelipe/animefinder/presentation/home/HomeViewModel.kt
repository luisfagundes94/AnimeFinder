package com.luisfelipe.animefinder.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.domain.enums.RequestStatus
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.domain.usecase.GetPopularAnimesFromApi
import kotlinx.coroutines.launch
import javax.inject.Named

class HomeViewModel @ViewModelInject constructor(
    @Named("getPopularAnimes") val getPopularAnimesFromApi: GetPopularAnimesFromApi
) : ViewModel() {

    private val popularAnimesLiveData = MutableLiveData<List<Anime>>()
    val popularAnimes: LiveData<List<Anime>> = popularAnimesLiveData

    private val failedToFetchAnimesLiveData = MutableLiveData<Int>()
    val failedToFetchAnimes: LiveData<Int> = failedToFetchAnimesLiveData

    fun getPopularAnimes() {
        viewModelScope.launch {
            val requestStatus = getPopularAnimesFromApi()
            handleRequestStatus(requestStatus)
        }
    }

    private fun handleRequestStatus(requestStatus: RequestStatus<List<Anime>>) {
        when (requestStatus) {
            is RequestStatus.Success -> popularAnimesLiveData.postValue(requestStatus.data)
            is RequestStatus.Error -> {
                failedToFetchAnimesLiveData.postValue(
                    R.string.warning_failed_to_fetch_popular_animes
                )
            }
            is RequestStatus.InProgress -> {}
        }
    }
}