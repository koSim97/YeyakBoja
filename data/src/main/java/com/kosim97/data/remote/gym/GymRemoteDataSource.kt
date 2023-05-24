package com.kosim97.data.remote.gym

import com.kosim97.data.model.GymApiData
import com.kosim97.data.model.GymApiList
import com.kosim97.domain.model.GymDomainList
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.repository.GymRemoteRepository
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GymRemoteDataSource{
    suspend fun getGymAllList(start: Int, end: Int): ApiResult<List<GymDomainModel>>

    suspend fun getGymClassList(start: Int, end: Int, minClass: String): ApiResult<List<GymDomainModel>>
}