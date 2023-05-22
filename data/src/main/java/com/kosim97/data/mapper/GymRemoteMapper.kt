package com.kosim97.data.mapper

import android.util.Log
import com.kosim97.data.model.GymApiData
import com.kosim97.data.model.GymApiList
import com.kosim97.domain.model.GymDomainData
import com.kosim97.domain.model.GymDomainList
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object GymRemoteResponseMapper {
    fun mapperTest(test: Response<GymApiList>): ApiResult<List<GymDomainModel>> {
        Log.d("test","qwe ${test.isSuccessful}")

        when (test.isSuccessful) {
            true -> {
                val test1 = test.body()?.list?.dataList?.map {
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
                return ApiResult.Success(test1)
            }
            else -> {
                return ApiResult.Fail(test.message())
            }
        }
    }

}