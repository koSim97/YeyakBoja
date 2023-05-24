package com.kosim97.data.remote.gym

import com.kosim97.data.api.GymApi
import com.kosim97.data.mapper.HomeRemoteResponseMapper
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import javax.inject.Inject

class GymRemoteDataSourceImp @Inject constructor(
    private val api: GymApi
): GymRemoteDataSource {
    override suspend fun getGymAllList(start: Int, end: Int): ApiResult<List<GymDomainModel>> {
        return HomeRemoteResponseMapper.gymDataMapper(api.getGymAllList(start, end))
    }

    override suspend fun getGymClassList(
        start: Int,
        end: Int,
        minClass: String
    ): ApiResult<List<GymDomainModel>> {
        return HomeRemoteResponseMapper.gymDataMapper(api.getGymClassList(start, end, minClass))
    }
}