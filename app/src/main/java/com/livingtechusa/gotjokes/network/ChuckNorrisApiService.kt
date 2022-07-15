package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants.BASE_URL_CHUCK_NORRIS
import com.livingtechusa.gotjokes.data.api.ApiConstants.END_POINT_CHUCK_NORRIS
import com.livingtechusa.gotjokes.data.api.model.ChuckNorris
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val chuckNorrisRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL_CHUCK_NORRIS)
    .build()

interface ChuckNorrisApiService {
    @GET(END_POINT_CHUCK_NORRIS)
    suspend fun getChuckNorrisJoke(): ChuckNorris

    object ChuckNorrisApi {
        val retrofitService: ChuckNorrisApiService by lazy {
            chuckNorrisRetrofit.create(ChuckNorrisApiService::class.java)
        }
    }
}