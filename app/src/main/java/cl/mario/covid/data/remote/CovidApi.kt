package cl.mario.covid.data.remote

import cl.mario.covid.data.models.CovidResults
import cl.mario.covid.util.Constants.Companion.TOTAL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CovidApi {

    @Headers("X-RapidAPI-Key:96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e")
    @GET(TOTAL)
    suspend fun getData(@Query("date") date:String): Response<CovidResults>
}