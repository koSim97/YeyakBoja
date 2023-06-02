package com.kosim97.data.repositoryImp.camping

import com.kosim97.data.mapper.HomeRemoteResponseMapper
import com.kosim97.data.remote.camping.CampingRemoteDataSource
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.repository.CampingRemoteRepository
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CampingRemoteRepositoryImp @Inject constructor(
    private val remote: CampingRemoteDataSource
): CampingRemoteRepository {
    override suspend fun getCampingList(
        start: Int,
        end: Int,
        minClass: String
    ): Flow<ApiResult<List<CampingDomainModel>>> = flow {
        emit(HomeRemoteResponseMapper.campingDataMapper(remote.getCampingList(start, end, minClass)))
    }

}