package com.hdd.kotlin_caf.services.authentication

import com.hdd.kotlin_caf.services.authentication.model.LoginRequest
import com.hdd.kotlin_caf.services.authentication.model.LoginResponse
import com.hdd.kotlin_caf.services.authentication.model.LoginSocialRequest
import rx.Observable

/**
 * Created on 3/2/2018.
 * @author duonghd
 */
interface AuthenticationManager<
        TLoginResponse : LoginResponse,
        TLoginRequest : LoginRequest,
        TLoginSocialRequest : LoginSocialRequest> {
    fun isAuthenticated(): Boolean

    fun isLoadFromStorageSuccess(): Boolean

    fun getLoginResponse(): TLoginResponse?

    fun setLoginResponse(tLoginResponse: TLoginResponse?)

    fun login(tLoginRequest: TLoginRequest): Observable<TLoginResponse>

    fun loginSocial(tLoginSocialRequest: TLoginSocialRequest): Observable<TLoginResponse>

    fun logout(): String
}