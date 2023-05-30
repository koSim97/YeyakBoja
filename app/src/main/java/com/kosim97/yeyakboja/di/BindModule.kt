package com.kosim97.yeyakboja.di

import com.kosim97.data.remote.camping.CampingRemoteDataSource
import com.kosim97.data.remote.camping.CampingRemoteDataSourceImp
import com.kosim97.data.remote.reserve.ReserveRemoteDataSource
import com.kosim97.data.remote.reserve.ReserveRemoteDataSourceImp
import com.kosim97.data.remote.gym.GymRemoteDataSource
import com.kosim97.data.remote.gym.GymRemoteDataSourceImp
import com.kosim97.data.repositoryImp.camping.CampingRemoteRepositoryImp
import com.kosim97.data.repositoryImp.gym.GymRemoteRepositoryImp
import com.kosim97.data.repositoryImp.reserve.ReserveRemoteRepositoryImp
import com.kosim97.domain.repository.CampingRemoteRepository
import com.kosim97.domain.repository.GymRemoteRepository
import com.kosim97.domain.repository.ReserveRemoteRepository
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

    @Binds
    @Singleton
    abstract fun bindCampingRemoteDataSource(
        campingRemoteDataSourceImp: CampingRemoteDataSourceImp
    ): CampingRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCampingRemoteRepository(
        campingRemoteRepositoryImp: CampingRemoteRepositoryImp
    ): CampingRemoteRepository

    @Binds
    @Singleton
    abstract fun bindReserveRemoteDataSource(
        reserveRemoteDataSourceImp: ReserveRemoteDataSourceImp
    ): ReserveRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindReserveRemoteRepository(
        reserveRemoteRepositoryImp: ReserveRemoteRepositoryImp
    ): ReserveRemoteRepository
}