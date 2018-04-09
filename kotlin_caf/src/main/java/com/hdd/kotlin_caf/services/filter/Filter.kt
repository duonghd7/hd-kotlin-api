package com.hdd.kotlin_caf.services.filter

/**
 * Created on 2/28/2018.
 * @author duonghd
 */


interface Filter<Input, Output> {
    fun execute(source: Input): Output
}
