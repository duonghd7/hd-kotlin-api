package com.hdd.kotlinapi.services.gcm

import com.hdd.kotlin_caf.services.common.RestMessageResponse
import com.hdd.kotlinapi.infastructures.models.gcm.GcmBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import rx.Observable

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

interface RestGcmService {

    @POST("/api/gcm/clear-token")
    fun clearGcmToken(@Body gcmBody: GcmBody): Observable<RestMessageResponse<String>>

    @POST("/api/gcm/update-token")
    fun updateGcmToken(@Header("access-token") accessToken: String,
                       @Body gcmBody: GcmBody): Observable<RestMessageResponse<Any>>
}