package com.livingtechusa.gotjokes.util

import com.livingtechusa.gotjokes.data.api.model.GoogleImages
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.data.api.model.Joke
import com.livingtechusa.gotjokes.data.api.model.Pexel
import com.livingtechusa.gotjokes.data.database.entity.ImageSearchEntity
import com.livingtechusa.gotjokes.data.database.entity.JokeEntity
import java.util.Date

fun Joke.toEntity() = JokeEntity(
    id,
    imageUrl,
    caption,
    dateAdded
)

fun JokeEntity.toModel() = Joke(
    id,
    imageUrl,
    caption,
    dateAdded
)

fun ImgFlip.Data.Meme.toImageSearchEntity() = ImageSearchEntity(
    url,
    Date(System.currentTimeMillis())
)

fun Pexel.Photo.Src.toImageSearchEntity() = ImageSearchEntity(
    medium,
    Date(System.currentTimeMillis())
)

fun GoogleImages.Item.Pagemap.toImageSearchEntity(): List<ImageSearchEntity> {

    val list = mutableListOf<ImageSearchEntity>()
    if (imageobject != null) {
        for(it in  imageobject){
            list.add(
                ImageSearchEntity(
                    it.url,
                    Date(System.currentTimeMillis())
                )
            )
        }
    }
    return list
}