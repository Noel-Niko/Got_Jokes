package com.livingtechusa.gotjokes.repositories.image_repos.imgflip

import com.livingtechusa.gotjokes.domain.model.Image

interface ImgFlipRepository {
    suspend fun get(): List<Image>
}