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
    var gymCategory: String,
    @SerializedName("SVCSTATNM")
    var gymActive: String,
    @SerializedName("SVCNM")
    var gymTitle: String,
    @SerializedName("PAYATNM")
    var gymFee: String,
    @SerializedName("PLACENM")
    var gymLocationName: String,
    @SerializedName("SVCURL")
    var gymURL: String,
    @SerializedName("X")
    var gymLocationX: String,
    @SerializedName("Y")
    var gymLocationY: String,
    @SerializedName("SVCOPNBGNDT")
    var gymServiceStart: String,
    @SerializedName("SVCOPNENDDT")
    var gymServiceEnd: String,
    @SerializedName("RCPTBGNDT")
    var gymActiveStart: String,
    @SerializedName("RCPTENDDT")
    var gymActiveEnd: String,
    @SerializedName("IMGURL")
    var gymImage: String,
    @SerializedName("TELNO")
    var gymPhone: String,
    @SerializedName("V_MIN")
    var gymUseStart: String,
    @SerializedName("V_MAX")
    var gymUseEnd: String
)
