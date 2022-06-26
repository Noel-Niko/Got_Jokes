package com.livingtechusa.gotjokes.network.model

import com.livingtechusa.gotjokes.Data
import com.livingtechusa.gotjokes.domain.model.Image
import com.livingtechusa.gotjokes.domain.util.DomainMapper

class responseDtoMapper: DomainMapper<Data, Image> {

    override fun mapToDomainModel(response: Data?): List<Image> {
        val images: MutableList<Image> = mutableListOf()
        if (response != null) {
            for (meme in response.memes) {
                val image = Image(
                    meme.url,
                    meme.height,
                    meme.width
                )
                images.add(image)
            }
        }
        return images
    }

    override fun mapFromDomainModel(domainModel: Image): Data {
        TODO("Not yet implemented")
    }


}