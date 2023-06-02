package com.kosim97.data.remote.gym

import com.kosim97.data.api.GymApi
import com.kosim97.data.model.GymApiList
import retrofit2.Response
import javax.inject.Inject

class GymRemoteDataSourceImp @Inject constructor(
    private val api: GymApi
): GymRemoteDataSource {
    override suspend fun getGymAllList(start: Int, end: Int): Response<GymApiList> {
        return api.getGymAllList(start, end)
    }

    override suspend fun getGymClassList(
        start: Int,
        end: Int,
        minClass: String
    ): Response<GymApiList> {
        return api.getGymClassList(start, end, minClass)
    }
}