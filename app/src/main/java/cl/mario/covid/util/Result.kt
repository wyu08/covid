package cl.mario.covid.util

sealed class Result<T> {
    data class Success<T>(val data: T?) : Result<T>()
    data class Error<T>(val message: String, val exception: Exception? = null) : Result<T>()
}