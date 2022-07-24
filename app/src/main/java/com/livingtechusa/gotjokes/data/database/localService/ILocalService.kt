package com.livingtechusa.gotjokes.data.database.localService

import com.livingtechusa.gotjokes.data.api.model.GoogleImages
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.data.api.model.Joke
import com.livingtechusa.gotjokes.data.api.model.Pexel
import com.livingtechusa.gotjokes.data.database.entity.ImageSearchEntity
import java.util.Date

interface ILocalService {
    suspend fun insertImgFlipMemeImage(imgFlipImage: ImgFlip.Data.Meme)
    suspend fun insertGoogleImages(searchImage: GoogleImages)
    suspend fun insertImgFlipMemeImageList(list: List<ImgFlip.Data.Meme>)
    suspend fun getOneImage(): ImageSearchEntity
    suspend fun getAllImages(): List<ImageSearchEntity>
    suspend fun clearOldImages(date: Date)
    suspend fun insertJoke(joke: Joke)
    suspend fun deleteJoke(joke: Joke)
    suspend fun getAllJokes():List<Joke>
    suspend fun clearJokesTable()
    suspend fun insertPexelImageList(pexel: Pexel)
}