package com.hdd.kotlin_caf.services.network

import android.content.Context
import com.hdd.kotlin_caf.services.common.RestMessageResponse
import com.hdd.kotlin_caf.services.filter.ApiThrowableFilter
import com.hdd.kotlin_caf.services.filter.NetworkFilter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created on 2/27/2018.
 * @author duonghd
 */

open class DefaultNetworkProvider(context: Context, baseUrl: String) : AbstractNetworkProvider(context), NetworkProvider {
    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

    override fun <T> provideApi(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    override fun <TResponse : RestMessageResponse<TResult>, TResult> transformResponse(call: Observable<TResponse>): Observable<TResult> {

        val res: Observable<TResult> = call
                .observeOn(Schedulers.computation())
                .onErrorResumeNext { throwable -> NetworkFilter<TResponse>(this).execute(throwable) }
                .onErrorResumeNext { throwable -> ApiThrowableFilter<TResponse>().execute(throwable) }
                .flatMap { tResponse -> Observable.just(tResponse.data) }

        return res.onExceptionResumeNext(Observable.empty())
    }
}
