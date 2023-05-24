package com.kosim97.domain.repository

import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface CampingRemoteRepository {
    suspend fun getCampingList(start: Int, end: Int, minClass: String): Flow<ApiResult<List<CampingDomainModel>>>
}