package com.livingtechusa.gotjokes.network


import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.ApiConstants.BASE_URL_PEXEL
import com.livingtechusa.gotjokes.data.api.model.Pexel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with Moshi
 * object.
 */
private val PexelRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL_PEXEL)
    .build()

/**
 * A public interface that exposes the [getPexelMeme] method
 *
 */
interface PexelApiService {
    @GET(ApiConstants.END_POINT_PEXEL)
    suspend fun getPexelMeme(@Header("Authorization") auth: String, @Query("page") page: String):  Pexel
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object PexelApi {
    val retrofitService: PexelApiService by lazy {
        PexelRetrofit.create(PexelApiService::class.java)
    }
}