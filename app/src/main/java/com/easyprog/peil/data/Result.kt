package com.easyprog.peil.data

sealed class Result<out T: Any> {
    data class SUCCESS<out T: Any>(val data: T): Result<T>()
    data class ERROR(val error: String): Result<Nothing>()
    object LOADING: Result<Nothing>()
}
