package com.kosim97.domain.di

import com.kosim97.domain.repository.GymRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

//    @Provides
//    @Singleton
//    fun provideGymRemoteRepository(
//
//    ): GymRemoteRepository {
//        return
//    }
}