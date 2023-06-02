package com.kosim97.data.model

import com.google.gson.annotations.SerializedName

data class CampingApiList(
    @SerializedName("ListPublicReservationInstitution")
    var list: CampingApiDataList
)

data class CampingApiDataList(
    @SerializedName("row")
    var dataList: ArrayList<CampingApiData>
)

data class CampingApiData(
    @SerializedName("MINCLASSNM")
    var campingCategory: String,
    @SerializedName("SVCSTATNM")
    var campingActive: String,
    @SerializedName("SVCNM")
    var campingTitle: String,
    @SerializedName("PAYATNM")
    var campingFee: String,
    @SerializedName("PLACENM")
    var campingLocationName: String,
    @SerializedName("SVCURL")
    var campingURL: String,
    @SerializedName("X")
    var campingLocationX: String,
    @SerializedName("Y")
    var campingLocationY: String,
    @SerializedName("SVCOPNBGNDT")
    var campingServiceStart: String,
    @SerializedName("SVCOPNENDDT")
    var campingServiceEnd: String,
    @SerializedName("RCPTBGNDT")
    var campingActiveStart: String,
    @SerializedName("RCPTENDDT")
    var campingActiveEnd: String,
    @SerializedName("IMGURL")
    var campingImage: String,
    @SerializedName("DTLCONT")
    var campingInfo: String,
    @SerializedName("TELNO")
    var campingPhone: String,
    @SerializedName("V_MIN")
    var campingUseStart: String,
    @SerializedName("V_MAX")
    var campingUseEnd: String
)
