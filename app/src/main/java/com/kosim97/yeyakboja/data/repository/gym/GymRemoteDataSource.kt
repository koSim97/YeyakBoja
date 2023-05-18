package com.kosim97.yeyakboja.data.repository.gym

import com.kosim97.yeyakboja.data.remote.model.GymApiList
import retrofit2.Response

interface GymRemoteDataSource {
    suspend fun getGymAllList(start: Int, end: Int): Response<GymApiList>
}