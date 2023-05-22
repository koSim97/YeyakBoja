package com.kosim97.domain.usecase

import android.util.Log
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.repository.GymRemoteRepository
import com.kosim97.domain.util.ApiResult
import com.kosim97.domain.util.BaseFlowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GymUseCase {
    suspend operator fun invoke(start: Int, end: Int): Flow<ApiResult<List<GymDomainModel>>>
}

class GymUseCaseImp @Inject constructor(
    private val repository: GymRemoteRepository
): GymUseCase {
    override suspend fun invoke(start: Int, end: Int): Flow<ApiResult<List<GymDomainModel>>> {
        return withContext(Dispatchers.IO) {
            repository.getGymAllList(start, end)
        }
    }
}