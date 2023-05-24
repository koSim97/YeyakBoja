package com.kosim97.data.remote.camping

import com.kosim97.data.api.CampingApi
import com.kosim97.data.mapper.HomeRemoteResponseMapper
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CampingRemoteDataSourceImp @Inject constructor(
    private val api: CampingApi
): CampingRemoteDataSource {
    override suspend fun getCampingList(
        start: Int,
        end: Int,
        minClass: String
    ): ApiResult<List<CampingDomainModel>> {
        return HomeRemoteResponseMapper.campingDataMapper(api.getCampingList(start, end, minClass))
    }
}