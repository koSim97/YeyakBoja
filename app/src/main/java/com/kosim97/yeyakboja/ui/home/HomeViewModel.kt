package com.kosim97.yeyakboja.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.usecase.*
import com.kosim97.domain.util.ApiResult
import com.kosim97.domain.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCampingData: GetCampingInfo,
    private val getGymClassData: GetGymClassInfo,
): ViewModel() {

    private val _campingList = MutableStateFlow(listOf<CampingDomainModel>())
    val campingList = _campingList.asStateFlow()

    private val _footballList = MutableStateFlow(listOf<GymDomainModel>())
    val footballList = _footballList.asStateFlow()

    private val _soccerList = MutableStateFlow(listOf<GymDomainModel>())
    val soccerList = _soccerList.asStateFlow()

    fun getCampingData() {
        viewModelScope.launch(Dispatchers.IO) {
            _campingList.emit(getCampingData(1, 100, "캠핑장"))
        }
    }

    fun getGymData(minClass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getGymClassData(1, 100, minClass)
            result.collectLatest {
                when (it) {
                    is UiState.Success -> {
                        if (minClass == "풋살장") {
                            it.list.let { it1 -> _footballList.emit(it1) }
                        } else if (minClass == "축구장") {
                            it.list.let { it1 -> _soccerList.emit(it1) }
                        }
                    }
                    else -> {
                        Log.d("test","vm fail ")
                    }
                }
            }

        }
    }
}