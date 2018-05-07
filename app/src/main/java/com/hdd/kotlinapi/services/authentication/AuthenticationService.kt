package com.hdd.kotlinapi.services.authentication

import com.hdd.kotlin_caf.services.authentication.AuthenticationManager
import com.hdd.kotlinapi.infastructures.models.account.LoginRequestBody
import com.hdd.kotlinapi.infastructures.models.account.LoginSocialRequestBody
import com.hdd.kotlinapi.infastructures.models.account.User
import rx.Observable

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

interface AuthenticationService : AuthenticationManager<User, LoginRequestBody, LoginSocialRequestBody> {
    fun authMe(): Observable<User>
}