package com.kosim97.data.repositoryImp.gym

import com.kosim97.data.remote.gym.GymRemoteDataSource
import com.kosim97.domain.model.GymDomainList
import com.kosim97.domain.repository.GymRemoteRepository
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GymRemoteRepositoryImp @Inject constructor(
    private val remote: GymRemoteDataSource
): GymRemoteRepository {

    override suspend fun getGymAllList(start: Int, end: Int): Flow<ApiResult<List<GymDomainModel>>> = flow {
        emit(remote.getGymAllList(start, end))
    }

    override suspend fun getGymClassList(
        start: Int,
        end: Int,
        minClass: String
    ): Flow<ApiResult<List<GymDomainModel>>> = flow {
        emit(remote.getGymClassList(start, end, minClass))
    }
}