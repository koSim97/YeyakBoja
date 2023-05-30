package com.kosim97.domain.usecase

import android.util.Log
import com.kosim97.domain.repository.ReserveRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

interface GetReserveDateList {
    suspend operator fun invoke(url: String): List<String>
}

class GetReserveDateListImp @Inject constructor(
    private val repository: ReserveRemoteRepository
): GetReserveDateList {
    override suspend fun invoke(url: String): List<String> {
        val docsList = mutableListOf<String>()
        withContext(Dispatchers.IO) {
            val docsConvert = repository.getReserveDataList(url)
            val docsArray= docsConvert.get("resultListDays") as JSONArray
            for (i in 0 until docsArray.length()) {
                val docsObj = docsArray.getJSONObject(i)
                if (docsObj.get("SVC_RESVE_CODE") == "Y") {
                    Log.d("test","asd $docsObj")
                    docsList.add(docsObj.get("YMD") as String)
                }
            }
        }
        return docsList
    }
}