package com.hdd.kotlin_caf.services.filter

import rx.Observable

/**
 * Created on 3/2/2018.
 * @author duonghd
 */

interface OutputFilter<InputTransformer, OutputTransformer> {
    fun execute(inputObservable: Observable<InputTransformer>): Observable<OutputTransformer>
}