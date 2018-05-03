package com.hdd.kotlinapi.domain.login

import com.hdd.kotlin_caf.exceptions.ApiThrowable
import com.hdd.kotlin_caf.scope.ActivityScope
import com.hdd.kotlinapi.domain.base.BasePresenter
import com.hdd.kotlinapi.infastructures.models.account.User
import com.hdd.kotlinapi.infastructures.models.gcm.GcmBody
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import com.hdd.kotlinapi.services.gcm.GcmService
import com.hdd.kotlinapi.utils.AppUtils
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

@ActivityScope
open class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {
    @Inject
    protected lateinit var gcmService: GcmService
    @Inject
    protected lateinit var authService: AuthenticationService

    private var subscription: Subscription? = null

    fun clearGcm() {
        onStop(subscription)
        subscription = gcmService.clearGcmToken(GcmBody(AppUtils.getDeviceToken(), "android"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _ ->
                    ifViewAttached {
                        view.clearGcmSuccess()
                    }
                }, { it: Throwable? ->
                    val throwable: Throwable = it as ApiThrowable
                    ifViewAttached {
                        view.apiError(throwable)
                    }
                })
    }

    fun updateGcm() {
        val user: User = authService.getLoginResponse()!!
        onStop(subscription)
        subscription = gcmService.updateGcmToken(user.getAccessToken(), GcmBody(AppUtils.getDeviceToken(), "android"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _ ->
                    ifViewAttached {
                        view.updateGcmSuccess()
                    }
                }, { it: Throwable? ->
                    val throwable: Throwable = it as ApiThrowable
                    ifViewAttached {
                        view.apiError(throwable)
                    }
                })
    }

    private fun onStop(subscription: Subscription?) {
        if (subscription != null && !subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }
}