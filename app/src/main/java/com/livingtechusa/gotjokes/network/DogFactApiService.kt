package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.model.DogFact
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val dogFactRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(ApiConstants.BASE_URL_DOG_FACTS)
    .build()

interface DogFactApiService {
    @GET(ApiConstants.END_POINT_DOG_FACTS)
    suspend fun getDogFact(): DogFact

    object DogFactApi {
        val retrofitService: DogFactApiService by lazy {
            dogFactRetrofit.create(DogFactApiService::class.java)
        }
    }
}