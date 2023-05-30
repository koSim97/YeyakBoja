package com.kosim97.data.repositoryImp.reserve

import com.kosim97.data.remote.reserve.ReserveRemoteDataSource
import com.kosim97.domain.repository.ReserveRemoteRepository
import org.json.JSONObject
import javax.inject.Inject

class ReserveRemoteRepositoryImp @Inject constructor(
    private val remote: ReserveRemoteDataSource
): ReserveRemoteRepository {
    override suspend fun getReserveDataList(url: String): JSONObject {
        return remote.getReserveList(url)
    }
}