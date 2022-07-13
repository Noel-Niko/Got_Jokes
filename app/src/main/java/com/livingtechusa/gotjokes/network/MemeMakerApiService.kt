package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.model.MemeMakerImage
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with Moshi
 * object.
 */
private val memeMakerRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(ApiConstants.BASE_URL_MEME_MAKER)
    .build()

/**
 * A public interface that exposes the [getMemeMakerImage] method
 */
interface MemeMakerApiService {
    @GET(ApiConstants.END_POINT_MEME_MAKER)
    suspend fun getMemeMakerImage(): MemeMakerImage
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MemeMakerApi {
    val retrofitService: MemeMakerApiService by lazy {
        memeMakerRetrofit.create(MemeMakerApiService::class.java)
    }
}