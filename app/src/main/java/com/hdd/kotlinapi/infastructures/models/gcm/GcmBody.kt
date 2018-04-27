package com.hdd.kotlinapi.infastructures.models.gcm

import java.io.Serializable

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

open class GcmBody(private val gcm_token: String,
                   private val gcm_platform: String) : Serializable