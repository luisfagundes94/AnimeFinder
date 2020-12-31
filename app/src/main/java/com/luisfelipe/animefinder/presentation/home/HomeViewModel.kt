package com.luisfelipe.animefinder.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.domain.enums.RequestStatus
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.domain.usecase.GetLatestAnimesFromApi
import com.luisfelipe.animefinder.domain.usecase.GetPopularAnimesFromApi
import com.luisfelipe.animefinder.domain.usecase.GetUpcomingAnimesFromApi
import kotlinx.coroutines.launch
import javax.inject.Named

class HomeViewModel @ViewModelInject constructor(
    @Named("getPopularAnimes") private val getPopularAnimesFromApi: GetPopularAnimesFromApi,
    @Named("getLatestAnimes") private val getLatestAnimesFromApi: GetLatestAnimesFromApi,
    @Named("getUpcomingAnimes") private val getUpcomingAnimesFromApi: GetUpcomingAnimesFromApi
) : ViewModel() {

    private val popularAnimesLiveData = MutableLiveData<List<Anime>>()
    val popularAnimes: LiveData<List<Anime>> = popularAnimesLiveData

    private val latestAnimesLiveData = MutableLiveData<List<Anime>>()
    val latestAnimes: LiveData<List<Anime>> = latestAnimesLiveData

    private val upcomingAnimesLiveData = MutableLiveData<List<Anime>>()
    val upcomingAnimes: LiveData<List<Anime>> = upcomingAnimesLiveData

    private val failedToFetchAnimesLiveData = MutableLiveData<Int>()
    val failedToFetchAnimes: LiveData<Int> = failedToFetchAnimesLiveData

    private val shimmerAnimationLiveData = MutableLiveData<Boolean>()
    val shimmerAnimation: LiveData<Boolean> = shimmerAnimationLiveData

    fun getPopularAnimes() = viewModelScope.launch {
        val requestStatus = getPopularAnimesFromApi()
        handleRequestStatus(requestStatus, popularAnimesLiveData)
    }

    fun getLatestAnimes() = viewModelScope.launch {
        val requestStatus = getLatestAnimesFromApi()
        handleRequestStatus(requestStatus, latestAnimesLiveData)
    }

    fun getUpcomingAnimes() = viewModelScope.launch {
        val requestStatus = getUpcomingAnimesFromApi()
        handleRequestStatus(requestStatus, upcomingAnimesLiveData)
    }

    private fun handleRequestStatus(
        requestStatus: RequestStatus<List<Anime>>,
        animesLiveData: MutableLiveData<List<Anime>>
    ) {
        when (requestStatus) {
            is RequestStatus.Success -> {
                shimmerAnimationLiveData.postValue(false)
                animesLiveData.postValue(requestStatus.data)
            }
            is RequestStatus.Error -> {
                shimmerAnimationLiveData.postValue(false)
                failedToFetchAnimesLiveData.postValue(
                    R.string.warning_failed_to_fetch_popular_animes
                )
            }
            is RequestStatus.InProgress -> {
            }
        }
    }
}