package com.kosim97.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GymDomainModel(
    var gymCategory: String,
    var gymActive: String,
    var gymTitle: String,
    var gymFee: String,
    var gymLocationName: String,
    var gymURL: String,
    var gymLocationX: String,
    var gymLocationY: String,
    var gymServiceStart: String,
    var gymServiceEnd: String,
    var gymActiveStart: String,
    var gymActiveEnd: String,
    var gymImage: String,
    var gymInfo: String,
    var gymPhone: String,
    var gymUseStart: String,
    var gymUseEnd: String
): Parcelable
