package com.livingtechusa.gotjokes.util

import com.livingtechusa.gotjokes.data.api.model.GoogleImages
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.data.api.model.Pexel
import com.livingtechusa.gotjokes.data.database.entity.ImageSearchEntity
import java.util.Date

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
        for (it in imageobject) {
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