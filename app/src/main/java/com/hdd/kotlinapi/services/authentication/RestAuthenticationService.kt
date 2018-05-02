package com.hdd.kotlinapi.services.authentication

import com.hdd.kotlin_caf.services.common.RestMessageResponse
import com.hdd.kotlinapi.infastructures.models.account.LoginRequestBody
import com.hdd.kotlinapi.infastructures.models.account.User
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import rx.Observable

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

interface RestAuthenticationService {
    @POST("/api/auth/sign-in")
    fun login(@Body bodyRequest: LoginRequestBody): Observable<RestMessageResponse<User>>

    @POST("/api/auth/sign-out")
    fun logout(@Header("access-token") accessToken: String): Observable<RestMessageResponse<String>>
}