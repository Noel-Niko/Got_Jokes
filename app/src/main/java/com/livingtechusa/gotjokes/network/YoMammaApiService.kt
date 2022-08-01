package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants.BASE_URL_YOMAMMA
import com.livingtechusa.gotjokes.data.api.ApiConstants.END_POINT_YOMAMMA
import com.livingtechusa.gotjokes.data.api.model.YoMamma
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val yoMammaRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL_YOMAMMA)
    .build()

interface YoMammaApiService {
    @GET(END_POINT_YOMAMMA)
    suspend fun getYoMammaJoke(): YoMamma
}

object YoMammaApi {
    val retrofitService: YoMammaApiService by lazy {
        yoMammaRetrofit.create(YoMammaApiService::class.java)
    }
}