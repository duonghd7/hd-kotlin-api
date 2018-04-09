package com.hdd.kotlin_caf.models

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

open class ServiceResultError(val errorCode: Int, val errorMessage: String, val exception: Exception)
