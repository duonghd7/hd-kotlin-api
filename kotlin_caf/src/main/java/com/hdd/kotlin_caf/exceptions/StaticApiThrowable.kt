package com.hdd.kotlin_caf.exceptions

import com.hdd.kotlin_caf.models.ServiceResultError

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

object StaticApiThrowable {
    fun from(listErrorResult: List<ServiceResultError>): ApiThrowable {
        return ApiThrowable(listErrorResult)
    }

    fun from(error: ServiceResultError): ApiThrowable {
        val listError: List<ServiceResultError> = mutableListOf(error)
        return from(listError)
    }

    fun from(errorCode: Int, errorMessage: String, ex: Exception): ApiThrowable {
        return from(ServiceResultError(errorCode, errorMessage, ex))
    }

    fun from(errorCode: Int, errorMessage: String): ApiThrowable {
        return from(errorCode, errorMessage, Exception(errorMessage))
    }

    fun from(exception: Exception): ApiThrowable {
        val messageEx: String = if (exception.message == null) exception.toString() else exception.message!!
        return from(ErrorCodes.GENERAL_ERROR, messageEx, exception)
    }
}