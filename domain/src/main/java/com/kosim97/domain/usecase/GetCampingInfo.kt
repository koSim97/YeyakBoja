package com.kosim97.domain.usecase

import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.repository.CampingRemoteRepository
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetCampingInfo {
    suspend operator fun invoke(start: Int, end: Int, minClass: String): Flow<ApiResult<List<CampingDomainModel>>>
}

class GetCampingInfoImp @Inject constructor(
    private val repository: CampingRemoteRepository
): GetCampingInfo {
    override suspend fun invoke(
        start: Int,
        end: Int,
        minClass: String
    ): Flow<ApiResult<List<CampingDomainModel>>> {
        return withContext(Dispatchers.IO) {
            repository.getCampingList(start, end, minClass)
        }
    }

}