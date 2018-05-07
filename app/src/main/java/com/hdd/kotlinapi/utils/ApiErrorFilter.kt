package com.hdd.kotlinapi.utils

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.hdd.kotlin_caf.exceptions.ApiThrowable
import com.hdd.kotlin_caf.exceptions.ErrorCodes
import com.hdd.kotlin_caf.exceptions.StaticApiThrowable
import com.hdd.kotlin_caf.services.authentication.AuthenticationManagerConfiguration
import com.hdd.kotlin_caf.services.filter.InterceptFilter
import com.hdd.kotlinapi.domain.login.LoginActivity_
import com.orhanobut.hawk.Hawk
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.net.ConnectException

/**
 * Created on 5/4/2018.
 * @author duonghd
 */

open class ApiErrorFilter(private val app: Application,
                          private val configuration: AuthenticationManagerConfiguration) : InterceptFilter {
    private val TAG = ApiErrorFilter::class.java.simpleName

    override fun <T> execute(): Observable.Transformer<T, T> {
        return Observable.Transformer { tObservable ->
            tObservable.observeOn(AndroidSchedulers.mainThread())
                    .onErrorResumeNext { throwable ->
                        var throwableResult = throwable
                        try {
                            if (throwable is ApiThrowable) {
                                if (throwable.firstErrorCode() == 2009) {
                                    /*
                                    * token expires
                                    * */
                                    Toast.makeText(app, throwable.firstErrorMessage(), Toast.LENGTH_SHORT).show()
                                    LoginActivity_.intent(app)
                                            .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .start()

                                    /*
                                    * clear user in local
                                    * */
                                    if (configuration.isUseStorage() && Hawk.isBuilt()) {
                                        Hawk.remove(configuration.getStorageKey())
                                    }
                                }
                            } else if (throwable is ConnectException) {
                                Log.i(TAG, "<-- $throwable")
                                throwableResult = StaticApiThrowable.from(ErrorCodes.GENERAL_ERROR, "$throwable")
                            }
                        } catch (ex: Exception) {
                            tObservable.onExceptionResumeNext(Observable.empty())
                        }

                        Observable.error(throwableResult)
                    }
        }
    }
}