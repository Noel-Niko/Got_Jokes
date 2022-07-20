package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.model.RandomFact
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val randomFactsRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(ApiConstants.BASE_URL_RANDOM_FACTS)
    .build()

interface RandomFactsApiService {
    @GET(ApiConstants.END_POINT_RANDOM_FACTS)
    suspend fun getRandomFacts(): RandomFact?


    object RandomFactsApi {
        val retrofitService: RandomFactsApiService by lazy {
            randomFactsRetrofit.create(RandomFactsApiService::class.java)
        }
    }
}

