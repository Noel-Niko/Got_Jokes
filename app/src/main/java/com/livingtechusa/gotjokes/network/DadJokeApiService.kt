package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.model.DadJokes
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val dadJokeRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(ApiConstants.BASE_URL_DAD_JOKES)
    .build()

interface DadJokeApiService {
    @GET(ApiConstants.END_POINT_DADJOKES)
    suspend fun getdadJoke(): DadJokes

    object DadJokeApi {
        val retrofitService: DadJokeApiService by lazy {
            dadJokeRetrofit.create(DadJokeApiService::class.java)
        }
    }
}