package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.ApiConstants.BASE_URL_GOOGLE_IMAGE
import com.livingtechusa.gotjokes.data.api.ApiConstants.END_POINT_GOOGLE_IMAGE1
import com.livingtechusa.gotjokes.data.api.ApiConstants.END_POINT_GOOGLE_IMAGE2
import com.livingtechusa.gotjokes.data.api.model.GoogleImages
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with Moshi
 * object.
 */
private val googleImageRetrofit = Retrofit.Builder()
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create().asLenient())
    .baseUrl(BASE_URL_GOOGLE_IMAGE)
    .build()

/**
 * A public interface that exposes the [getGoogleImages] method
 */
interface GoogleImagesApiService { // id=07f5aa9fbf617a226 key=AIzaSyAw4gfjQSBxieq3NGg8iydmlcjkdrOC_74
    @GET(END_POINT_GOOGLE_IMAGE1)
    suspend fun getGoogleImages(): GoogleImages

    @GET(END_POINT_GOOGLE_IMAGE2)
    suspend fun getNextPageGoogleImages(): GoogleImages
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object GoogleImageApi {
    val retrofitService: GoogleImagesApiService by lazy {
        googleImageRetrofit.create(GoogleImagesApiService::class.java)
    }
}