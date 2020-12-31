package com.luisfelipe.animefinder.domain.enums

sealed class RequestStatus<out T: Any> {
    data class Success<out T: Any>(val data: T) : RequestStatus<T>()
    data class Error(val exception: String) : RequestStatus<Nothing>()
    object InProgress : RequestStatus<Nothing>()
}