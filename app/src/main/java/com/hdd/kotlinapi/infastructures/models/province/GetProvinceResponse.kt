package com.hdd.kotlinapi.infastructures.models.province

import com.hdd.kotlin_caf.services.common.RestErrorResponse

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

class GetProvinceResponse(val data: List<Province>, val errors: List<RestErrorResponse>, val requestUrl: String)