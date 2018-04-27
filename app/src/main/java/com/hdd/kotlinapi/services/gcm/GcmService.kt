package com.hdd.kotlinapi.services.gcm

import com.hdd.kotlinapi.infastructures.models.gcm.GcmBody
import rx.Observable

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

interface GcmService {
    fun clearGcmToken(gcmBody: GcmBody): Observable<String>

    fun updateGcmToken(accessToken:String, gcmBody: GcmBody): Observable<Any>
}