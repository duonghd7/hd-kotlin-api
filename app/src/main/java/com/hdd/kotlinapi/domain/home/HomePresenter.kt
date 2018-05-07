package com.hdd.kotlinapi.domain.home

import com.hdd.kotlin_caf.exceptions.ApiThrowable
import com.hdd.kotlin_caf.scope.ActivityScope
import com.hdd.kotlinapi.domain.base.BasePresenter
import com.hdd.kotlinapi.infastructures.models.account.User
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

@ActivityScope
class HomePresenter @Inject constructor() : BasePresenter<HomeView>() {
    @Inject
    protected lateinit var authService: AuthenticationService

    fun isLogined(): Boolean {
        return authService.isLoadFromStorageSuccess()
    }

    fun getUserInfo(): User? {
        return authService.getLoginResponse()
    }

    fun authMe() {
        authService.authMe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { ifViewAttached { view -> view.showLoading() } }
                .subscribe({ it ->
                    val user = it
                    ifViewAttached {
                        view.hideLoading()
                        view.authMeSuccess(user)
                    }
                }, { it: Throwable? ->
                    val throwable: Throwable = it as ApiThrowable
                    ifViewAttached {
                        view.hideLoading()
                        view.apiError(throwable)
                    }
                })
    }

    fun logout() {
        authService.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { ifViewAttached { view -> view.showLoading() } }
                .subscribe({ _ ->
                    ifViewAttached {
                        view.hideLoading()
                        view.logoutSuccess()
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