package com.hdd.kotlinapi.services.province

import com.hdd.kotlin_caf.services.common.RestMessageResponse
import com.hdd.kotlinapi.infastructures.models.province.Province
import retrofit2.http.GET
import rx.Observable

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

interface RestProvinceService {

    @GET("/api/province")
    fun getProvince(): Observable<RestMessageResponse<List<Province>>>
}