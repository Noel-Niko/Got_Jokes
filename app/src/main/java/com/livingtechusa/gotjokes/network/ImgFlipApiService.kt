package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.ApiConstants.BASE_URL_IMGFLIP
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with Moshi
 * object.
 */
private val imgFlipRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL_IMGFLIP)
    .build()

/**
 * A public interface that exposes the [getImgFlipMeme] method
 */
interface ImgFlipApiService {
    @GET(ApiConstants.END_POINT_IMGFLIP)
    suspend fun getImgFlipMeme():  ImgFlip?
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ImgFlipApi {
    val retrofitService: ImgFlipApiService by lazy {
        imgFlipRetrofit.create(ImgFlipApiService::class.java)
    }
}