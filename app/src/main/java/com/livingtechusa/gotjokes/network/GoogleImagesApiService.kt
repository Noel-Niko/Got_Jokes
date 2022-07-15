package com.livingtechusa.gotjokes.network

import com.livingtechusa.gotjokes.data.api.model.GoogleImages
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with Moshi
 * object.
 */
private val googleImageRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create().asLenient())
    .baseUrl(  "https://www.googleapis.com/customsearch/")   //"https://cse.google.com/cse/element/")
    .build()

/**
 * A public interface that exposes the [getGoogleImages] method
 */
interface GoogleImagesApiService { // id=07f5aa9fbf617a226 key=AIzaSyAw4gfjQSBxieq3NGg8iydmlcjkdrOC_74
    @GET("v1?key=AIzaSyAw4gfjQSBxieq3NGg8iydmlcjkdrOC_74&searchtype=image&cx=07f5aa9fbf617a226&q=funny%20image")
    suspend fun getGoogleImages(): GoogleImages

    @GET("v1?key=AIzaSyAw4gfjQSBxieq3NGg8iydmlcjkdrOC_74&searchtype=image&cx=07f5aa9fbf617a226&q=funny%20image&hl=en&tbs&sa=X&ved=0CAEQpwVqFwoTCMiwiZHN-fgCFQAAAAAdAAAAABAF")
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