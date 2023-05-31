package com.kosim97.yeyakboja.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.usecase.GetCampingInfoImp
import com.kosim97.domain.usecase.GetGymClassInfoImp
import com.kosim97.domain.usecase.GymUseCaseImp
import com.kosim97.domain.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gymUseCase: GymUseCaseImp,
    private val getGymClassData: GetGymClassInfoImp,
    private val getCampingData: GetCampingInfoImp
): ViewModel() {

    private val _campingList = MutableStateFlow(listOf<CampingDomainModel>())
    val campingList = _campingList.asStateFlow()

    private val _footballList = MutableStateFlow(listOf<GymDomainModel>())
    val footballList = _footballList.asStateFlow()

    private val _soccerList = MutableStateFlow(listOf<GymDomainModel>())
    val soccerList = _soccerList.asStateFlow()

    fun getCampingData() {
        viewModelScope.launch(Dispatchers.IO) {
            getCampingData(1, 100, "캠핑장")
                .collectLatest {
                    when (it) {
                        is ApiResult.Success -> {
                            it.data?.let { data ->
                                val convertData = data.filter {filterData ->
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

                                _campingList.emit(convertData)
                            }
                        }
                        else -> {
                            Log.d("test","fail $it")
                        }
                    }
                }
        }
    }

    fun getGymData(minClass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getGymClassData(1, 100, minClass)
                .collectLatest {
                    when (it) {
                        is ApiResult.Success -> {
                            it.data?.let { data ->
                                val convertData = data.filter { filterData ->
                                    filterData.gymActive == "접수중"
                                }
                                convertData.map { item ->
                                    item.gymTitle = item.gymTitle.replace("&lt;","<")
                                    item.gymTitle = item.gymTitle.replace("&gt;",">")
                                    item.gymServiceStart = item.gymServiceStart.substring(0,10)
                                    item.gymServiceEnd = item.gymServiceEnd.substring(0,10)
                                    item.gymActiveStart = item.gymActiveStart.substring(0,10)
                                    item.gymActiveEnd = item.gymActiveEnd.substring(0,10)
                                }
                                if (minClass == "풋살장") {
                                    _footballList.emit(convertData)
                                } else if (minClass =="축구장") {
                                    _soccerList.emit(convertData)
                                }

                            }
                        }
                        else -> {
                            Log.d("test","fail $it")
                        }
                    }
                }
        }
    }
}