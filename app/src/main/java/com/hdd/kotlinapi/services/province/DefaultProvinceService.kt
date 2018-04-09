package com.hdd.kotlinapi.services.province

import com.hdd.kotlin_caf.services.network.NetworkProvider
import com.hdd.kotlinapi.infastructures.models.province.Province
import rx.Observable

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

open class DefaultProvinceService(
        private val networkProvider: NetworkProvider,
        private val restProvinceService: RestProvinceService) : ProvinceService {

    override fun getProvince(): Observable<List<Province>> {
        return networkProvider.transformResponse(restProvinceService.getProvince())
    }
}