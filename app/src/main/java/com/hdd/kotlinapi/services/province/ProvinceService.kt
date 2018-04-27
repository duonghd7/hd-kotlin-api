package com.hdd.kotlinapi.services.province

import com.hdd.kotlinapi.infastructures.models.province.Province
import rx.Observable

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

interface ProvinceService {
    fun getProvince(): Observable<List<Province>>
}