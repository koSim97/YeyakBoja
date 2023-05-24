package com.kosim97.data.api

import com.kosim97.data.model.CampingApiList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CampingApi {
    @GET("ListPublicReservationInstitution/{START_INDEX}/{END_INDEX}/{MINCLASSNM}")
    suspend fun getCampingList(
        @Path("START_INDEX")
        start: Int,
        @Path("END_INDEX")
        end: Int,
        @Path("MINCLASSNM")
        minClass: String
    ): Response<CampingApiList>
}