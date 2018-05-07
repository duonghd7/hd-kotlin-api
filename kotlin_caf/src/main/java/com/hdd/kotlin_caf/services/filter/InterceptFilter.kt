package com.hdd.kotlin_caf.services.filter

import rx.Observable

/**
 * Created on 5/4/2018.
 * @author duonghd
 */
interface InterceptFilter {
    fun <T> execute(): Observable.Transformer<T, T>
}