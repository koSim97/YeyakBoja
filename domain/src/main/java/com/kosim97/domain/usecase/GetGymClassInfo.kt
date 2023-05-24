package com.kosim97.domain.usecase

import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.repository.GymRemoteRepository
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetGymClassInfo {
    suspend operator fun invoke(start: Int, end: Int, minClass: String): Flow<ApiResult<List<GymDomainModel>>>
}

class GetGymClassInfoImp @Inject constructor(
    private val repository: GymRemoteRepository
): GetGymClassInfo {
    override suspend fun invoke(
        start: Int,
        end: Int,
        minClass: String
    ): Flow<ApiResult<List<GymDomainModel>>> {
        return withContext(Dispatchers.IO) {
            repository.getGymClassList(start, end, minClass)
        }
    }

}