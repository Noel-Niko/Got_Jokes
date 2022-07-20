package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants.BASE_URL_JOKE_API
import com.livingtechusa.gotjokes.data.api.ApiConstants.END_POINT_JOKE_API
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val jokeRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL_JOKE_API)
    .build()

interface JokeApiService {
    @GET(END_POINT_JOKE_API)
    suspend fun getJokeApiJoke(): com.livingtechusa.gotjokes.data.api.model.JokeApi

    object JokeApi {
        val retrofitService: JokeApiService by lazy {
            jokeRetrofit.create(JokeApiService::class.java)
        }
    }
}