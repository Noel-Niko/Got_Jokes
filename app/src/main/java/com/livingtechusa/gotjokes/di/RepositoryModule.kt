package com.livingtechusa.gotjokes.di

import com.livingtechusa.gotjokes.network.model.responseDtoMapper
import com.livingtechusa.gotjokes.network.webservices.ImgFlipService
import com.livingtechusa.gotjokes.repositories.image_repos.imgflip.ImgFlipRepository
import com.livingtechusa.gotjokes.repositories.image_repos.imgflip.ImgFlipRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideImgFlipRepository(
        imgFlipService: ImgFlipService
    ): ImgFlipRepository {
        return ImgFlipRepository_Impl(
            imgFlipService = imgFlipService,
        )
    }
}