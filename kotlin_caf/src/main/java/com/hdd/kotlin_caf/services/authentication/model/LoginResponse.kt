package com.hdd.kotlin_caf.services.authentication.model

/**
 * Created on 3/2/2018.
 * @author duonghd
 */

interface LoginResponse {
    fun getUsername(): String

    fun setUsername(username: String)

    fun getEmail(): String

    fun setEmail(email: String)

    fun getAccessToken(): String

    fun setAccessToken(accessToken: String)
}