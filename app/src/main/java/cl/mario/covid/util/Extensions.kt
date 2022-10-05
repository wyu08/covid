package cl.mario.covid.util

import cl.mario.covid.data.models.CovidResultsData
import cl.mario.covid.data.remote.CovidApi
import retrofit2.Response

fun <T> T?.orElse(value: T) = this ?: value

suspend fun CovidApi.getDataSafe(date:String): Response<CovidResultsData>? =
    try {
        this.getData(date)
    }catch (e:Exception) {
        null
    }
