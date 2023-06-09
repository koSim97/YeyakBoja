package com.kosim97.data.repositoryImp.gym

import com.kosim97.data.mapper.HomeRemoteResponseMapper
import com.kosim97.data.remote.gym.GymRemoteDataSource
import com.kosim97.domain.repository.GymRemoteRepository
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GymRemoteRepositoryImp @Inject constructor(
    private val remote: GymRemoteDataSource
): GymRemoteRepository {

    override suspend fun getGymAllList(start: Int, end: Int): ApiResult<List<GymDomainModel>>{
        return HomeRemoteResponseMapper.gymDataMapper(remote.getGymAllList(start, end))
    }

    override suspend fun getGymClassList(
        start: Int,
        end: Int,
        minClass: String
    ): ApiResult<List<GymDomainModel>> {
        return HomeRemoteResponseMapper.gymDataMapper(remote.getGymClassList(start, end, minClass))
    }
}