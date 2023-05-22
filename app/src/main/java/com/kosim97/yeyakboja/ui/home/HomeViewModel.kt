package com.kosim97.yeyakboja.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosim97.domain.usecase.GymUseCaseImp
import com.kosim97.domain.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GymUseCaseImp
): ViewModel() {

    fun getGymAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.invoke(0,0)
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
}