package com.hdd.kotlin_caf.services.common

/**
 * Created on 2/27/2018.
 * @author duonghd
 */

open class RestMessageResponse<T>(val data: T, val errors: List<RestErrorResponse>)
