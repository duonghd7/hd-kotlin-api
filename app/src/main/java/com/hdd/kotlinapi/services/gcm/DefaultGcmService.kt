package com.hdd.kotlinapi.services.gcm

import com.hdd.kotlin_caf.services.network.NetworkProvider
import com.hdd.kotlinapi.infastructures.models.gcm.GcmBody
import rx.Observable

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

open class DefaultGcmService(private val networkProvider: NetworkProvider,
                             private val restGcmService: RestGcmService) : GcmService {

    override fun clearGcmToken(gcmBody: GcmBody): Observable<String> {
        return networkProvider.transformResponse(restGcmService.clearGcmToken(gcmBody))
    }

    override fun updateGcmToken(accessToken: String, gcmBody: GcmBody): Observable<Any> {
        return networkProvider.transformResponse(restGcmService.updateGcmToken(accessToken, gcmBody))
    }
}