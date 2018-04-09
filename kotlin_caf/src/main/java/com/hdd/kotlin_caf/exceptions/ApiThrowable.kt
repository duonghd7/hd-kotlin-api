package com.hdd.kotlin_caf.exceptions

import com.hdd.kotlin_caf.models.ServiceResultError

/**
 * Created on 2/28/2018.
 * @author duonghd
 */


open class ApiThrowable(val errors: List<ServiceResultError>) : Throwable() {
    fun firstError(): ServiceResultError {
        return errors[0]
    }

    fun firstErrorCode(): Int {
        return firstError().errorCode
    }

    fun firstErrorMessage(): String {
        return firstError().errorMessage
    }
}
