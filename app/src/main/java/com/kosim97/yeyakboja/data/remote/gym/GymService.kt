package com.kosim97.yeyakboja.data.remote.gym

import com.kosim97.yeyakboja.data.remote.model.GymApiList
import com.kosim97.yeyakboja.data.repository.gym.GymRemoteDataSource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GymService: GymRemoteDataSource {
    @GET("ListPublicReservationSport/{START_INDEX}/{END_INDEX}")
    override suspend fun getGymAllList(
        @Path("START_INDEX")
        start: Int,
        @Path("END_INDEX")
        end: Int): Response<GymApiList>
}