package com.hdd.kotlin_caf.base

import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

abstract class DBaseFragment<V : MvpView, P : MvpPresenter<V>> : MvpFragment<V, P>() {

    protected open fun showHUD() {}

    protected open fun hideHUD() {}

    protected open fun showError(throwable: Throwable) {}
}