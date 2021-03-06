package com.hdd.kotlin_caf.base

import android.support.annotation.UiThread
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

abstract class DBaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>() {

    @UiThread
    protected open fun showHUD() {}

    @UiThread
    protected open fun hideHUD() {}

    protected open fun showError(throwable: Throwable) {}
}