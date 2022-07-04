package com.livingtechusa.gotjokes.data.api

import com.livingtechusa.gotjokes.data.api.ApiConstants.END_POINT_IMGFLIP
import com.livingtechusa.gotjokes.data.api.model.imgFlip
import retrofit2.http.GET

interface ImgFlipApi {
    @GET(END_POINT_IMGFLIP)
    suspend fun getImgFlipMeme(): List<imgFlip.Data.Meme>
}