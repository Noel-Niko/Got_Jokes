package com.livingtechusa.gotjokes.di

import com.livingtechusa.gotjokes.data.database.localService.ILocalService
import com.livingtechusa.gotjokes.data.database.localService.LocalServiceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class LocalServiceModule {
    @Binds
    abstract fun providesLocalService(impl: LocalServiceProvider) : ILocalService
}