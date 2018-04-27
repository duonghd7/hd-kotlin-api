package com.hdd.kotlin_caf.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

abstract class DBasePresenter<V : MvpView> : MvpBasePresenter<V>() {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    override fun destroy() {
        disposables.clear()
    }
}