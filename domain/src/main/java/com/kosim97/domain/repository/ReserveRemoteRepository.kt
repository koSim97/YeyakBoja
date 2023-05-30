package com.kosim97.domain.repository

import org.json.JSONObject

interface ReserveRemoteRepository {

    suspend fun getReserveDataList(url: String): JSONObject
}