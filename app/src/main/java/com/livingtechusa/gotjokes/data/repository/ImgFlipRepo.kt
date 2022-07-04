package com.livingtechusa.gotjokes.data.repository

import com.livingtechusa.gotjokes.data.api.ImgFlipApi
import com.livingtechusa.gotjokes.data.api.model.imgFlip
import javax.inject.Inject

class ImgFlipRepo @Inject constructor(
    private val imgFlipApi: ImgFlipApi
){
    suspend fun getImgFlipApi(): List<imgFlip.Data.Meme>{
        return imgFlipApi.getImgFlipMeme()
    }
}