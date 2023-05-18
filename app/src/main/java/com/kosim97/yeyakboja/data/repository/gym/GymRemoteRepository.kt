package com.kosim97.yeyakboja.data.repository.gym

import com.kosim97.yeyakboja.data.remote.model.GymApiList
import com.kosim97.yeyakboja.data.util.ApiResult
import com.kosim97.yeyakboja.data.util.BaseFlowResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GymRemoteRepository @Inject constructor(
    private val remote: GymRemoteDataSource
):BaseFlowResponse() {

    fun getGymApiData(start: Int, end: Int): Flow<ApiResult<GymApiList>> = flow {
        emit(flowCall(remote.getGymAllList(start, end)))
    }
}