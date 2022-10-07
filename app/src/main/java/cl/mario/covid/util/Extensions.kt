package cl.mario.covid.util

import retrofit2.Response
import java.util.Calendar
import java.util.Date

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

fun dateToStringApi(date: Date): String {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val monthformat = calendar.get(Calendar.MONTH).plus(1).toString().padStart(2,'0')
    val dayformat = calendar.get(Calendar.DATE).toString().padStart(2,'0')
    return "${calendar.get(Calendar.YEAR)}-$monthformat-$dayformat"
}