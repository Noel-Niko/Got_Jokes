//package com.livingtechusa.gotjokes.data.repository
//
//import com.livingtechusa.gotjokes.network.IimgFlipApiService
//import com.livingtechusa.gotjokes.data.api.model.ImgFlip
//import javax.inject.Inject
//
//class ImgFlipRepo @Inject constructor(
//    private val imgFlipApi: IimgFlipApiService
//){
//    suspend fun getImgFlipApi(): List<ImgFlip.Data.Meme>{
//        return imgFlipApi.getImgFlipMeme()
//    }
//}