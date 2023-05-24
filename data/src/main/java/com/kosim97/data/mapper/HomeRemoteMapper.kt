package com.kosim97.data.mapper

import android.util.Log
import com.kosim97.data.model.CampingApiList
import com.kosim97.data.model.GymApiList
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import retrofit2.Response

object HomeRemoteResponseMapper {
    fun gymDataMapper(responseData: Response<GymApiList>): ApiResult<List<GymDomainModel>> {
        Log.d("test","qwe ${responseData.isSuccessful}")

        when (responseData.isSuccessful) {
            true -> {
                val homeData = responseData.body()?.list?.dataList?.map {
                    GymDomainModel(
                        it.gymCategory,
                        it.gymActive,
                        it.gymTitle,
                        it.gymFee,
                        it.gymLocationName,
                        it.gymURL,
                        it.gymLocationX,
                        it.gymLocationY,
                        it.gymServiceStart,
                        it.gymServiceEnd,
                        it.gymActiveStart,
                        it.gymActiveEnd,
                        it.gymImage,
                        it.gymPhone,
                        it.gymUseStart,
                        it.gymUseEnd
                    )
                }
                return ApiResult.Success(homeData)
            }
            else -> {
                return ApiResult.Fail(responseData.message())
            }
        }
    }

    fun campingDataMapper(responseData: Response<CampingApiList>): ApiResult<List<CampingDomainModel>> {
        Log.d("test","qwe ${responseData.isSuccessful}")

        when (responseData.isSuccessful) {
            true -> {
                val campingData = responseData.body()?.list?.dataList?.map {
                    CampingDomainModel(
                        it.gymCategory,
                        it.gymActive,
                        it.gymTitle,
                        it.gymFee,
                        it.gymLocationName,
                        it.gymURL,
                        it.gymLocationX,
                        it.gymLocationY,
                        it.gymServiceStart,
                        it.gymServiceEnd,
                        it.gymActiveStart,
                        it.gymActiveEnd,
                        it.gymImage,
                        it.gymPhone,
                        it.gymUseStart,
                        it.gymUseEnd
                    )
                }
                return ApiResult.Success(campingData)
            }
            else -> {
                return ApiResult.Fail(responseData.message())
            }
        }
    }

}