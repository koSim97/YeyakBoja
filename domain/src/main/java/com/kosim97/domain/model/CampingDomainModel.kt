package com.kosim97.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CampingDomainModel(
    var campingCategory: String,
    var campingActive: String,
    var campingTitle: String,
    var campingFee: String,
    var campingLocationName: String,
    var campingURL: String,
    var campingLocationX: String,
    var campingLocationY: String,
    var campingServiceStart: String,
    var campingServiceEnd: String,
    var campingActiveStart: String,
    var campingActiveEnd: String,
    var campingImage: String,
    var campingPhone: String,
    var campingUseStart: String,
    var campingUseEnd: String
): Parcelable
