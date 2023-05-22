package com.kosim97.data.api

import com.kosim97.data.model.GymApiList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GymApi {
    @GET("ListPublicReservationSport/{START_INDEX}/{END_INDEX}")
    suspend fun getGymAllList(
        @Path("START_INDEX")
        start: Int,
        @Path("END_INDEX")
        end: Int): Response<GymApiList>
}