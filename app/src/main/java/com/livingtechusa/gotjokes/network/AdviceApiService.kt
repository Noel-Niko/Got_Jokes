package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.model.Advice
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val adviceRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(ApiConstants.BASE_URL_ADVICE)
    .build()

interface AdviceApiService {
    @GET(ApiConstants.END_POINT_ADVICE)
    suspend fun getadviceJoke(): Advice?

    object AdviceApi {
        val retrofitService: AdviceApiService by lazy {
            adviceRetrofit.create(AdviceApiService::class.java)
        }
    }
}