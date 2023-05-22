package com.kosim97.yeyakboja.di

import com.kosim97.data.remote.gym.GymRemoteDataSource
import com.kosim97.data.remote.gym.GymRemoteDataSourceImp
import com.kosim97.data.repositoryImp.gym.GymRemoteRepositoryImp
import com.kosim97.domain.repository.GymRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun bindGymRemoteDataSource(
        gymRemoteDataSourceImp: GymRemoteDataSourceImp
    ) : GymRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindGymRemoteRepository(
        gymRemoteRepositoryImp: GymRemoteRepositoryImp
    ): GymRemoteRepository
}