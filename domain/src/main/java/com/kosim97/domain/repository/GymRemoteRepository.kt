package com.kosim97.domain.repository


import com.kosim97.domain.model.GymDomainList
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GymRemoteRepository {
    suspend fun getGymAllList(start: Int, end: Int): Flow<ApiResult<List<GymDomainModel>>>
}