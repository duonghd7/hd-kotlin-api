package com.hdd.kotlinapi.services.authentication

import com.hdd.kotlin_caf.services.authentication.AbstractAuthenticationManager
import com.hdd.kotlin_caf.services.authentication.AuthenticationManagerConfiguration
import com.hdd.kotlin_caf.services.network.NetworkProvider
import com.hdd.kotlinapi.filter.AuthenticationLogoutSuccessFilter
import com.hdd.kotlinapi.filter.AuthenticationMeSuccessFilter
import com.hdd.kotlinapi.filter.AuthenticationSuccessFilter
import com.hdd.kotlinapi.infastructures.models.account.LoginRequestBody
import com.hdd.kotlinapi.infastructures.models.account.LoginSocialRequestBody
import com.hdd.kotlinapi.infastructures.models.account.User
import rx.Observable

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

open class DefaultAuthenticationService(
        private val networkProvider: NetworkProvider,
        private val restAuthenticationService: RestAuthenticationService,
        authenticationManagerConfiguration: AuthenticationManagerConfiguration)
    : AbstractAuthenticationManager<User, LoginRequestBody, LoginSocialRequestBody>(authenticationManagerConfiguration),
        AuthenticationService {

    override fun login(tLoginRequest: LoginRequestBody): Observable<User> {
        return networkProvider.transformResponse(restAuthenticationService.login(tLoginRequest))
                .compose({ AuthenticationSuccessFilter(this).execute(it) })
    }

    override fun loginSocial(tLoginSocialRequest: LoginSocialRequestBody): Observable<User> {
        TODO("not implemented")
    }

    override fun logout(): Observable<String> {
        return networkProvider.transformResponse(restAuthenticationService.logout(this.getLoginResponse()!!.getAccessToken()))
                .compose({ AuthenticationLogoutSuccessFilter(this).execute(it) })
    }

    override fun authMe(): Observable<User> {
        return networkProvider.transformResponse(restAuthenticationService.authMe(this.getLoginResponse()!!.getAccessToken()))
                .compose({ AuthenticationMeSuccessFilter(this).execute(it) })
    }
}