package com.livingtechusa.gotjokes.di

import android.content.Context
import androidx.room.Room
import com.livingtechusa.gotjokes.data.database.AppDatabase
import com.livingtechusa.gotjokes.data.database.dao.ImageSearchDao
import com.livingtechusa.gotjokes.data.database.dao.JokeDao
import com.livingtechusa.gotjokes.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideImageSearchDao(appDatabase: AppDatabase): ImageSearchDao {
        return appDatabase.imageSearchDao()
    }
    @Provides
    fun provideJokeDao(appDatabase: AppDatabase): JokeDao {
        return appDatabase.jokeDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context):
        AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}