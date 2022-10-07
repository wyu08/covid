package cl.mario.covid.util

import retrofit2.Response

fun <T> T?.orElse(value: T) = this ?: value

suspend fun <T> safeRequest(request: suspend () -> Response<T>): Result<T> =
     try {
        val response =  request()
        if(response.isSuccessful){
            Result.Success(response.body())
        }else{
            Result.Error(response.message())
        }
    }catch (e: Exception){
        Result.Error(e.message.orElse("Something went wrong."),e)
    }
