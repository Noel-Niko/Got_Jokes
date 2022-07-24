package com.livingtechusa.gotjokes.data.database.localService

import com.livingtechusa.gotjokes.data.api.model.GoogleImages
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.data.api.model.Joke
import com.livingtechusa.gotjokes.data.api.model.Pexel
import com.livingtechusa.gotjokes.data.database.dao.ImageSearchDao
import com.livingtechusa.gotjokes.data.database.dao.JokeDao
import com.livingtechusa.gotjokes.data.database.entity.ImageSearchEntity
import com.livingtechusa.gotjokes.util.toEntity
import com.livingtechusa.gotjokes.util.toImageSearchEntity
import com.livingtechusa.gotjokes.util.toModel
import java.util.Date
import javax.inject.Inject

class LocalServiceProvider @Inject constructor(
    private val jokeDao: JokeDao,
    private val imageDao: ImageSearchDao
) : ILocalService {
    override suspend fun insertImgFlipMemeImage(imgFlipImage: ImgFlip.Data.Meme) {
        val image = imgFlipImage.toImageSearchEntity()
        imageDao.insertImage(image)
    }

    override suspend fun insertGoogleImages(searchImage: GoogleImages) {
        val item = searchImage.items.listIterator()
        item.forEach {
            val list = it.pagemap.toImageSearchEntity()
            for (img in list) {
                imageDao.insertImage(img)
            }
        }
    }

    override suspend fun insertImgFlipMemeImageList(list: List<ImgFlip.Data.Meme>) {
        for (meme in list) {
            imageDao.insertImage(meme.toImageSearchEntity())
        }
    }

    override suspend fun insertPexelImageList(pexel: Pexel) {
        for (photo in pexel.photos) {
            imageDao.insertImage(photo.src.toImageSearchEntity())
        }
    }

    override suspend fun getOneImage(): ImageSearchEntity {
        return imageDao.getOneFromImageSearchTable()
    }

    override suspend fun getAllImages(): List<ImageSearchEntity> {
        return imageDao.getAllFromImageSearchTable()
    }

    override suspend fun clearOldImages(date: Date) {
        imageDao.clearImageSearchTable(date)
    }

    override suspend fun insertJoke(joke: Joke) {
        jokeDao.insertJoke(joke.toEntity())
    }

    override suspend fun deleteJoke(joke: Joke) {
        jokeDao.removeJoke(joke.id)
    }

    override suspend fun getAllJokes(): List<Joke> {
        val list = mutableListOf<Joke>()
        for (joke in jokeDao.getAllFromJokeTable()) {
            list.add(joke.toModel())
        }
        return list
    }

    override suspend fun clearJokesTable() {
        jokeDao.clearJokeTable()
    }
}