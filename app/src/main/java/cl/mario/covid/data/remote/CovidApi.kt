package cl.mario.covid.data.remote

import cl.mario.covid.data.models.CovidResultsData
import cl.mario.covid.util.Constants.Companion.TOTAL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidApi {
    @GET(TOTAL)
    suspend fun getData(@Query("date") date:String): Response<CovidResultsData>
}