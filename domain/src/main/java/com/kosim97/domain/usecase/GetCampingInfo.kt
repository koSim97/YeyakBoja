package com.kosim97.domain.usecase

import android.util.Log
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.repository.CampingRemoteRepository
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetCampingInfo {
    suspend operator fun invoke(start: Int, end: Int, minClass: String): List<CampingDomainModel>
}

class GetCampingInfoImp @Inject constructor(
    private val repository: CampingRemoteRepository
): GetCampingInfo {
    override suspend fun invoke(
        start: Int,
        end: Int,
        minClass: String
    ): List<CampingDomainModel> {
        var convertData: List<CampingDomainModel> = listOf()
        withContext(Dispatchers.IO) {
            repository.getCampingList(start, end, minClass).collectLatest {
                when (it) {
                    is ApiResult.Success -> {
                        it.data?.let { data ->
                            convertData = data.filter {filterData ->
                                filterData.campingActive == "접수중"
                            }
                            convertData.map { item ->
                                item.campingTitle = item.campingTitle.replace("&lt;","<")
                                item.campingTitle = item.campingTitle.replace("&gt;",">")
                                item.campingServiceStart = item.campingServiceStart.substring(0,10)
                                item.campingServiceEnd = item.campingServiceEnd.substring(0,10)
                                item.campingActiveStart = item.campingActiveStart.substring(0,10)
                                item.campingActiveEnd = item.campingActiveEnd.substring(0,10)
                            }
                        }
                    }
                    else -> {
                        Log.d("test","fail $it")
                    }
                }
            }
        }
        val result = convertData.sortedByDescending {sort ->
            sort.campingActiveStart.replace("-","")
        }
        return result
    }

}