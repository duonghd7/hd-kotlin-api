package com.hdd.kotlin_caf.services.filter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hdd.kotlin_caf.exceptions.ApiThrowable
import com.hdd.kotlin_caf.exceptions.ErrorCodes
import com.hdd.kotlin_caf.exceptions.StaticApiThrowable
import com.hdd.kotlin_caf.models.ServiceResultError
import com.hdd.kotlin_caf.services.common.RestErrorResponse
import com.hdd.kotlin_caf.services.common.RestMessageResponse
import retrofit2.adapter.rxjava.HttpException
import rx.Observable

/**
 * Created on 2/28/2018.
 * @author duonghd
 */


open class ApiThrowableFilter<T> : Filter<Throwable, Observable<T>> {

    private fun onHandleFailedResponse(responseCode: Int, rawString: String): ApiThrowable {
        try {
            val gson = Gson()
            val collectionType = object : TypeToken<RestMessageResponse<T>>() {}.type
            val responseMessage: RestMessageResponse<RestErrorResponse> = gson.fromJson(rawString, collectionType)

            val errors: List<RestErrorResponse> = responseMessage.errors
            if (errors.isEmpty()) {
                return StaticApiThrowable.from(responseCode, rawString)
            } else {
                var errorResult: List<ServiceResultError> = mutableListOf<ServiceResultError>()
                for (error: RestErrorResponse in errors) {
                    errorResult += ServiceResultError(error.errorCode, error.errorMessage, Exception(error.errorMessage))
                }
                return StaticApiThrowable.from(errorResult)
            }
        } catch (ex: Exception) {
            return StaticApiThrowable.from(ex)
        }
    }

    override fun execute(source: Throwable): Observable<T> {
        if (source is HttpException) {
            val failedResponse = source.response().errorBody()
            val responseCode = source.response().code()

            if (failedResponse == null) {
                return Observable.error(StaticApiThrowable.from(responseCode, "Response Error Body is empty"))
            } else {
                var rawString = ""
                return try {
                    rawString = failedResponse.string()
                    Observable.error(onHandleFailedResponse(responseCode, rawString))
                } catch (ex: Exception) {
                    Observable.error(StaticApiThrowable.from(ErrorCodes.GENERAL_ERROR, rawString, ex))
                }
            }
        }
        return Observable.error(Throwable(source))
    }
}