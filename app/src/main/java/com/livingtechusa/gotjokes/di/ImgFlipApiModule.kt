package com.livingtechusa.gotjokes.di

import com.livingtechusa.gotjokes.data.api.ApiConstants.BASE_URL_IMGFLIP
import com.livingtechusa.gotjokes.data.api.ImgFlipApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object ImgFlipApiModule {
    @Provides
    @Singleton
    fun provideImgFlipApi(builder:Retrofit.Builder): ImgFlipApi{
        return builder
            .build()
            .create(ImgFlipApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImgFlipRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_IMGFLIP)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}