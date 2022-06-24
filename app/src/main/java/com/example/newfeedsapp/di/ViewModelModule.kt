package com.example.newfeedsapp.di

import com.example.newfeedsapp.data.repositories.AppRepository
import com.example.newfeedsapp.domain.repositories.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun bindRepository(appRepository: AppRepository): Repository
}