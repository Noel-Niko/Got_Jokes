package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.model.CatFact
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val catFactRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(ApiConstants.BASE_URL_CAT_FACTS)
    .build()

interface CatFactApiService {
    @GET(ApiConstants.END_POINT_CAT_FACTS)
    suspend fun getcatFact(): CatFact?

    object CatFactApi {
        val retrofitService: CatFactApiService by lazy {
            catFactRetrofit.create(CatFactApiService::class.java)
        }
    }
}