package com.livingtechusa.gotjokes.network.webservices

import com.livingtechusa.gotjokes.network.responses.ImgFlipResponse
import retrofit2.http.GET

interface ImgFlipService {
    @GET("get_memes/")
    suspend fun search(): ImgFlipResponse
}