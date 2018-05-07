package com.hdd.kotlin_caf.services.network

import com.hdd.kotlin_caf.services.common.RestMessageResponse
import com.hdd.kotlin_caf.services.filter.InterceptFilter
import rx.Observable

/**
 * Created on 2/27/2018.
 * @author duonghd
 */

interface NetworkProvider {
    fun isNetworkAvailable(): Boolean

    fun <T> provideApi(apiClass: Class<T>): T

    fun setApiErrorFilter(apiErrorFilter: InterceptFilter): NetworkProvider

    fun <TResponse : RestMessageResponse<TResult>, TResult> transformResponse(call: Observable<TResponse>): Observable<TResult>
}
