package com.hdd.kotlin_caf.base

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

interface DBaseView : MvpView {
    fun showLoading()

    fun hideLoading()

    fun apiError(throwable: Throwable)
}