package com.kosim97.yeyakboja.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosim97.domain.usecase.GetReserveDateListImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getReserveDateList: GetReserveDateListImp
): ViewModel() {
    var isGymData = MutableStateFlow(false)
    private val _reserveList = MutableSharedFlow<List<String>>(0)
    val reserveList = _reserveList.asSharedFlow()

     fun getReserveList(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _reserveList.emit(getReserveDateList(url))
        }
    }
}