package com.livingtechusa.gotjokes.di

import android.content.Context
import com.livingtechusa.gotjokes.BaseApplication
import com.livingtechusa.gotjokes.data.database.localService.ILocalService
import com.livingtechusa.gotjokes.data.database.localService.LocalServiceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
abstract class LocalServiceModule {
    @Binds
    abstract fun providesLocalService(impl: LocalServiceProvider) : ILocalService
}
