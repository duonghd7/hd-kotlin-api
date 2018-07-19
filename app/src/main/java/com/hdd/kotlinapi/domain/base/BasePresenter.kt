package com.hdd.kotlinapi.domain.base

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hdd.kotlin_caf.base.DBasePresenter

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

abstract class BasePresenter<V : MvpView> : DBasePresenter<V>()