package com.kosim97.data.remote.reserve

import org.json.JSONObject

interface ReserveRemoteDataSource {

    suspend fun getReserveList(url: String): JSONObject
}