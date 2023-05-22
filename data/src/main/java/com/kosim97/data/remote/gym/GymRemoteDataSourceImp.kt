package com.kosim97.data.remote.gym

import com.kosim97.data.api.GymApi
import com.kosim97.data.mapper.GymRemoteResponseMapper
import com.kosim97.data.model.GymApiList
import com.kosim97.domain.model.GymDomainList
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import javax.inject.Inject

class GymRemoteDataSourceImp @Inject constructor(
    private val api: GymApi
): GymRemoteDataSource {
    override suspend fun getGymAllList(start: Int, end: Int): ApiResult<List<GymDomainModel>> {
        return GymRemoteResponseMapper.mapperTest(api.getGymAllList(start, end))
    }
}