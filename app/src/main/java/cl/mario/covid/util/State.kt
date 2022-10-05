package cl.mario.covid.util

sealed class State<T> {
    class Loading<T> : State<T>()
    data class Error<T>(val message: String) : State<T>()
    data class Success<T>(val data: T) : State<T>()
    companion object {
        fun <T> loading(): State<T> = Loading()
        fun <T> success(data: T): State<T> = Success(data)
        fun <T> error(message: String): State<T> = Error(message)
    }
}