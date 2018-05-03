package com.hdd.kotlinapi.filter

import com.hdd.kotlin_caf.services.filter.OutputFilter
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import rx.Observable

/**
 * Created on 5/3/2018.
 * @author duonghd
 */

open class AuthenticationLogoutSuccessFilter(private val authenticationService: AuthenticationService) : OutputFilter<String, String> {

    override fun execute(inputObservable: Observable<String>): Observable<String> {
        return inputObservable.flatMap { t ->
            authenticationService.setLoginResponse(null)
            Observable.just(t)
        }
    }
}