package com.hdd.kotlinapi.domain.login.fragment.sign_in

import com.hdd.kotlin_caf.exceptions.ApiThrowable
import com.hdd.kotlin_caf.scope.ActivityScope
import com.hdd.kotlinapi.domain.base.BasePresenter
import com.hdd.kotlinapi.infastructures.models.account.LoginRequestBody
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

@ActivityScope
open class SignInPresenter @Inject constructor() : BasePresenter<SignInView>() {
    @Inject
    protected lateinit var authService: AuthenticationService

    fun login(loginRequestBody: LoginRequestBody) {
        authService.login(loginRequestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { ifViewAttached { view -> view.showLoading() } }
                .subscribe({ it ->
                    ifViewAttached {
                        view.hideLoading()
                        view.loginSuccess()
                    }
                }, { it: Throwable? ->
                    val throwable: Throwable = it as ApiThrowable
                    ifViewAttached {
                        view.hideLoading()
                        view.apiError(throwable)
                    }
                })
    }
}