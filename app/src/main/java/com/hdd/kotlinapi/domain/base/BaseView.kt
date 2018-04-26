package com.hdd.kotlinapi.domain.base

import com.hannesdorfmann.mosby.mvp.MvpView

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

interface BaseView : MvpView {
    fun showLoading()

    fun hideLoading()

    fun apiError(throwable: Throwable)
}