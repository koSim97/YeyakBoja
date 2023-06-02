package com.kosim97.domain.usecase

import android.util.Log
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.repository.GymRemoteRepository
import com.kosim97.domain.util.ApiResult
import com.kosim97.domain.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetGymClassInfo {
    suspend operator fun invoke(
        start: Int,
        end: Int,
        minClass: String
    ): Flow<UiState>
}

class GetGymClassInfoImp @Inject constructor(
    private val repository: GymRemoteRepository
) : GetGymClassInfo {
    override suspend fun invoke(
        start: Int,
        end: Int,
        minClass: String
    ): Flow<UiState> = flow {
        var convertData: List<GymDomainModel> = listOf()
        kotlin.runCatching {
            repository.getGymClassList(start, end, minClass)
        }.onSuccess {
            when (it) {
                is ApiResult.Success -> {
                    it.data?.let { data ->
                        convertData = data.filter { filterData ->
                            filterData.gymActive == "접수중"
                        }
                        convertData.map { item ->
                            item.gymTitle = item.gymTitle.replace("&lt;", "<")
                            item.gymTitle = item.gymTitle.replace("&gt;", ">")
                            item.gymServiceStart = item.gymServiceStart.substring(0, 10)
                            item.gymServiceEnd = item.gymServiceEnd.substring(0, 10)
                            item.gymActiveStart = item.gymActiveStart.substring(0, 10)
                            item.gymActiveEnd = item.gymActiveEnd.substring(0, 10)
                        }
                    }
                    val result = convertData.sortedByDescending { sort ->
                        sort.gymActiveStart.replace("-", "")
                    }
                    emit(UiState.Success(result))
                }
                else -> {
                    emit(UiState.Error("error"))
                }
            }
        }.onFailure {
            emit(UiState.Error(it.message))
        }

//        repository.getGymClassList(start, end, minClass)
//            .flowOn(Dispatchers.IO)
//            .collectLatest {
//                when (it) {
//                    is ApiResult.Success -> {
//                        it.data?.let { data ->
//                            convertData = data.filter { filterData ->
//                                filterData.gymActive == "접수중"
//                            }
//                            convertData.map { item ->
//                                item.gymTitle = item.gymTitle.replace("&lt;", "<")
//                                item.gymTitle = item.gymTitle.replace("&gt;", ">")
//                                item.gymServiceStart = item.gymServiceStart.substring(0, 10)
//                                item.gymServiceEnd = item.gymServiceEnd.substring(0, 10)
//                                item.gymActiveStart = item.gymActiveStart.substring(0, 10)
//                                item.gymActiveEnd = item.gymActiveEnd.substring(0, 10)
//                            }
//                        }
//                    }
//                    else -> {
//                        Log.d("test", "fail $it")
//                    }
//                }
//            }
//        val result = convertData.sortedByDescending { sort ->
//            sort.gymActiveStart.replace("-", "")
//        }
//        emit(UiState.Success(result))
    }
}