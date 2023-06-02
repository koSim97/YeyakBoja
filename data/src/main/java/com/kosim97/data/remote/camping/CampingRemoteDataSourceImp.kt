package com.kosim97.data.remote.camping

import com.kosim97.data.api.CampingApi
import com.kosim97.data.model.CampingApiList
import retrofit2.Response
import javax.inject.Inject

class CampingRemoteDataSourceImp @Inject constructor(
    private val api: CampingApi
): CampingRemoteDataSource {
    override suspend fun getCampingList(
        start: Int,
        end: Int,
        minClass: String
    ): Response<CampingApiList> {
        return api.getCampingList(start, end, minClass)
    }
}