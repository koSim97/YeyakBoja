package com.kosim97.data.remote.gym

import com.kosim97.data.model.GymApiList
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import retrofit2.Response

interface GymRemoteDataSource{
    suspend fun getGymAllList(start: Int, end: Int): Response<GymApiList>

    suspend fun getGymClassList(start: Int, end: Int, minClass: String): Response<GymApiList>
}