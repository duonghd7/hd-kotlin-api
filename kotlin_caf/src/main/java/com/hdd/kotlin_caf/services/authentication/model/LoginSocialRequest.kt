package com.hdd.kotlin_caf.services.authentication.model

/**
 * Created on 3/2/2018.
 * @author duonghd
 */

interface LoginSocialRequest {
    fun getAccessToken(): String

    fun setAccessToken(accessToken: String)

    fun getPlatform(): String

    fun setPlatform(platform: String)
}