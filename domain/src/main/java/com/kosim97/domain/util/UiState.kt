package com.kosim97.domain.util

import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.model.GymDomainModel

sealed class UiState {
    data class Success(val list: List<GymDomainModel>): UiState()
    data class Error(val message: String?): UiState()
}
