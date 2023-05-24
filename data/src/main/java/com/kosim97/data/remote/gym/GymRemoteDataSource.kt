package com.kosim97.data.remote.gym

import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult

interface GymRemoteDataSource{
    suspend fun getGymAllList(start: Int, end: Int): ApiResult<List<GymDomainModel>>

    suspend fun getGymClassList(start: Int, end: Int, minClass: String): ApiResult<List<GymDomainModel>>
}