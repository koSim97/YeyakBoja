package com.kosim97.data.remote.camping

import com.kosim97.data.model.CampingApiList
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CampingRemoteDataSource {
    suspend fun getCampingList(start: Int, end: Int, minClass: String): Response<CampingApiList>
}