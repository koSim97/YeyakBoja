package com.kosim97.domain.repository


import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface GymRemoteRepository {
    suspend fun getGymAllList(start: Int, end: Int): Flow<ApiResult<List<GymDomainModel>>>

    suspend fun getGymClassList(start: Int, end: Int, minClass: String): Flow<ApiResult<List<GymDomainModel>>>
}