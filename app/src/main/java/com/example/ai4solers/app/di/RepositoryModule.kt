package com.example.ai4solers.app.di

import com.example.ai4solers.data.repository.AIProcessingRepositoryImpl
import com.example.ai4solers.domain.repository.IAIProcessingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAIProcessingRepository(
        aiProcessingRepositoryImpl: AIProcessingRepositoryImpl
    ): IAIProcessingRepository
}