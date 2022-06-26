package com.livingtechusa.gotjokes.repositories.image_repos.imgflip

import com.livingtechusa.gotjokes.domain.model.Image
import com.livingtechusa.gotjokes.network.model.responseDtoMapper
import com.livingtechusa.gotjokes.network.webservices.ImgFlipService

class ImgFlipRepository_Impl(
    private val imgFlipService: ImgFlipService,
): ImgFlipRepository {
    override suspend fun get(): List<Image> {
        return  responseDtoMapper().mapToDomainModel(imgFlipService.search().data)

    }


}