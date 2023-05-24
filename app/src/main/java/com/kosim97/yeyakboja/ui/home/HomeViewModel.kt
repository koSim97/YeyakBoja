package com.kosim97.yeyakboja.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosim97.domain.usecase.CampingUseCaseImp
import com.kosim97.domain.usecase.GymClassUseCaseImp
import com.kosim97.domain.usecase.GymUseCaseImp
import com.kosim97.domain.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gymUseCase: GymUseCaseImp,
    private val gymClassUsecase: GymClassUseCaseImp,
    private val campingUseCaseImp: CampingUseCaseImp
): ViewModel() {

    fun getGymAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            gymUseCase.invoke(1,10)
                .collectLatest {
                    when(it) {
                        is ApiResult.Success -> {
                            Log.d("test","asd ${it.data}")
                        }
                        else -> {
                            Log.d("test","asd1 ${it}")
                        }
                    }

                }
        }
    }

    fun getGymClassData() {
        viewModelScope.launch(Dispatchers.IO) {
            gymClassUsecase.invoke(1, 10, "풋살장")
                .collectLatest {
                    when (it) {
                        is ApiResult.Success -> {
                            Log.d("test","success ${it.data}")
                        }
                        else -> {
                            Log.d("test","fail $it")
                        }
                    }
                }
        }
    }

    fun getCampingData() {
        viewModelScope.launch(Dispatchers.IO) {
            campingUseCaseImp.invoke(1, 10, "캠핑장")
                .collectLatest {
                    when (it) {
                        is ApiResult.Success -> {
                            Log.d("test","camping ${it.data}")
                        }
                        else -> {
                            Log.d("test","fail $it")
                        }
                    }
                }
        }
    }
}