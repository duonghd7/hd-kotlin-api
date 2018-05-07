package com.hdd.kotlinapi.filter

import com.hdd.kotlin_caf.services.filter.OutputFilter
import com.hdd.kotlinapi.infastructures.models.account.User
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import rx.Observable

/**
 * Created on 5/4/2018.
 * @author duonghd
 */

open class AuthenticationMeSuccessFilter(private val authenticationService: AuthenticationService) : OutputFilter<User, User> {

    override fun execute(inputObservable: Observable<User>): Observable<User> {
        return inputObservable.flatMap { t ->
            t.setAccessToken(authenticationService.getLoginResponse()!!.getAccessToken())
            authenticationService.setLoginResponse(t)
            Observable.just(t)
        }
    }
}