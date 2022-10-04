package cl.mario.covid.di

import cl.mario.covid.data.remote.CovidApi
import cl.mario.covid.data.remote.CovidInterceptor
import cl.mario.covid.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CovidResultsModule {
    @Provides
    @Singleton
    fun provideClientHttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(CovidInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetroFitInstance(): CovidApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClientHttp())
            .build()
            .create(CovidApi::class.java)
}