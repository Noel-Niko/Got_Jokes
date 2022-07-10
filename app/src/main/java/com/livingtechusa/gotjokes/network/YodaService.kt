package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants.BASE_URL_YODA
import com.livingtechusa.gotjokes.data.api.ApiConstants.END_POINT_YODA
import com.livingtechusa.gotjokes.data.api.model.YodaSpeak
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val yodaRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL_YODA)
    .build()

interface YodaService {
    @GET(END_POINT_YODA)
    suspend fun getYodaSpeak(
        @Query("text") text: String
    ): YodaSpeak?


object YodaSpeakApi {
    val retrofitService: YodaService by lazy {
        yodaRetrofit.create(YodaService::class.java)
    }
}


}