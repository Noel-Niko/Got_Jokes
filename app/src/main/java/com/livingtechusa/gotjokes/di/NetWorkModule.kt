package com.livingtechusa.gotjokes.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.livingtechusa.gotjokes.network.webservices.ImgFlipService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    var okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideImgFlipService(): ImgFlipService{
        return Retrofit.Builder()
            .baseUrl("https://api.imgflip.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().setLenient().create()))
            .build()
            .create(ImgFlipService::class.java)
    }


//    @Provides
//    @Singleton
//    fun getGson(): Gson {
//        return GsonBuilder().serializeNulls().setLenient().create()
//    }
//
//    @Provides
//    @Singleton
//    fun getInterceptor(): Interceptor {
//        return Interceptor {
//            val request = it.request().newBuilder()
//            val actualRequest = request.build()
//            it.proceed(actualRequest)
//        }
//    }
//    @Provides
//    @Singleton
//    fun getOkHttpClient(
//        interceptor: Interceptor
//    ): OkHttpClient {
//        val httpBuilder = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .addNetworkInterceptor(StethoInterceptor())
//
//        return httpBuilder
//            .protocols(mutableListOf(Protocol.HTTP_1_1))
//            .build()
//    }




}