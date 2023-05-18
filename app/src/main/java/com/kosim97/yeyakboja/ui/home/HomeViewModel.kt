package com.kosim97.yeyakboja.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosim97.yeyakboja.data.repository.gym.GymRemoteRepository
import com.kosim97.yeyakboja.data.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GymRemoteRepository
): ViewModel() {

    fun getGymAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGymApiData(1,5)
                .collectLatest {
                    when (it) {
                        is ApiResult.Success -> {
                            Log.d("test","${it.data?.list?.dataList}")
                        }
                        else -> {

                        }
                    }
                }
        }
    }
}