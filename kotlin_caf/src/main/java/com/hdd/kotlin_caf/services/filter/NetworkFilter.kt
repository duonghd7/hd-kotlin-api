package com.hdd.kotlin_caf.services.filter

import com.hdd.kotlin_caf.exceptions.ErrorCodes
import com.hdd.kotlin_caf.exceptions.StaticApiThrowable
import com.hdd.kotlin_caf.services.network.NetworkProvider
import rx.Observable

/**
 * Created on 2/28/2018.
 * @author duonghd
 */


open class NetworkFilter<T>(val networkProvider: NetworkProvider) : Filter<Throwable, Observable<T>> {

    override fun execute(source: Throwable): Observable<T> {
        return if (!networkProvider.isNetworkAvailable()) {
            Observable.error(StaticApiThrowable.from(ErrorCodes.NETWORK_NOT_AVAILABLE_ERROR,
                    "Network is not available!"))
        } else Observable.error(source)
    }
}
